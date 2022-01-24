package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.InvestigacionTipo;
import co.edu.itp.ciecyt.domain.Modalidad;
import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.service.InvestigacionTipoService;
import co.edu.itp.ciecyt.service.ModalidadService;
import co.edu.itp.ciecyt.service.ProgramaService;
import co.edu.itp.ciecyt.service.ProyectoPredictService;
import co.edu.itp.ciecyt.service.RolesModalidadService;
import co.edu.itp.ciecyt.service.dto.InvestigacionTipoDTO;
import co.edu.itp.ciecyt.service.dto.ModalidadDTO;
import co.edu.itp.ciecyt.service.dto.ProgramaDTO;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.ProtectedProperties;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;

@Service
@Transactional
public class ProyectoPreditcServiceImpl implements ProyectoPredictService {

    private final Logger log = LoggerFactory.getLogger(ProyectoPreditcServiceImpl.class);

    private final ProyectoRepository proyectoRepository;
    private final IntegranteProyectoService integranteProyectoService;
    //private final IntegranteProyectoRepository integranteProyectoRepository;
    private final RolesModalidadService rolesModalidadService;

    private final ModalidadService modalidadService;

    private final ProyectoMapper proyectoMapper;
    private DataSourceProperties dataSourceProperties;

    private InvestigacionTipoService investigacionTipoService;

    private ProgramaService programaService;

    public ProyectoPreditcServiceImpl(
        ProyectoRepository proyectoRepository,
        ProyectoMapper proyectoMapper,
        IntegranteProyectoService integranteProyectoService,
        RolesModalidadService rolesModalidadService,
        DataSourceProperties dataSourceProperties,
        InvestigacionTipoService investigacionTipoService,
        ProgramaService programaService,
        ModalidadService modalidadService
    ) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.integranteProyectoService = integranteProyectoService;
        this.rolesModalidadService = rolesModalidadService;
        this.dataSourceProperties = dataSourceProperties;
        this.investigacionTipoService = investigacionTipoService;
        this.programaService = programaService;
        this.modalidadService = modalidadService;
    }

    public Optional<ProyectoDTO> predicePlay(Long facultad, Long modalidad) throws Exception {
        //traer todos los registros de programa
        List<ProgramaDTO> programaDTOs;
        String programaCad = "";
        programaDTOs = programaService.findAll();
        List<String> programas = new ArrayList<String>();
        for (ProgramaDTO pDto : programaDTOs) {
            programas.add(pDto.getPrograma());
            programaCad += "'" + pDto.getPrograma() + "',";
            System.out.println(pDto.getPrograma());
        }
        programaCad = programaCad.substring(0, programaCad.length() - 1);

        //traer todos los registros de tipo
        List<InvestigacionTipoDTO> investigacionTipoDTOs;
        investigacionTipoDTOs = investigacionTipoService.findAll();
        String tipoCad = "";
        List<String> tipos = new ArrayList<String>();
        for (InvestigacionTipoDTO iDto : investigacionTipoDTOs) {
            tipos.add(iDto.getInvestigacionTipo());
            tipoCad += "'" + iDto.getInvestigacionTipo() + "',";
            System.out.println(iDto.getInvestigacionTipo());
        }
        tipoCad = tipoCad.substring(0, tipoCad.length() - 1);
        //////////////////////
        //traer todos los registros de modalidad
        List<Modalidad> modalidads;
        String modalidadCad = "";
        modalidads = modalidadService.buscarAll();
        List<String> modalidades = new ArrayList<String>();
        for (Modalidad mo : modalidads) {
            modalidades.add(mo.getModalidad());
            modalidadCad += "'" + mo.getModalidad() + "',";
            System.out.println(mo.getModalidad());
        }
        modalidadCad = modalidadCad.substring(0, modalidadCad.length() - 1);

        //////////////////////////
        Proyecto dataProyecto = new Proyecto();
        //List<Proyecto> lDataProyecto = proyectoRepository.findByFacultadIdAndProyectoModalidadId(facultad, modalidad);
        List<Proyecto> lDataProyecto = proyectoRepository.findAll();
        String info = "";
        for (Proyecto p : lDataProyecto) {
            info += "'";
            info += p.getPrograma();
            info += "'";
            info += ",";
            info += "'";
            info += p.getProyectoModalidad().getModalidad();
            info += "'";
            info += ",";
            info += "'";
            info += p.getTipo();
            info += "'";
            info += "\n";
        }

        //Declaring reference of File class
        File file = null;
        //Declaring reference of FileWriter class
        FileWriter filewriter = null;
        String data =
            "@relation Proyectos\n" +
            //"@attribute programa {"+//'Ingenieria de Sistemas','Tecnologia en Gestion Empresarial y de la innovacion','Administracion de Empresas','Desarrollo de Software','Tecnologia en Saneamiento Ambiental'}" +
            "@attribute programa {" +
            programaCad +
            "}" +
            "\n" +
            "@attribute modalidad {" +
            modalidadCad +
            "}" +
            "\n" +
            //"@attribute tipo {Teorica,aplicada}" +
            "@attribute tipo {" +
            tipoCad +
            "}" +
            "\n" +
            "@data" +
            "\n" +
            info;
        try {
            file = new File("wkfile.arff");
            //Creating Object of FileWriter class
            filewriter = new FileWriter(file);
            //Writing to the file
            filewriter.write(data);
            //Closing the stream
            filewriter.close();
            System.out.println("File writing done.");
        } catch (Exception e) { //Handing Exception
            e.printStackTrace();
        } finally {
            try {
                if (filewriter != null) {
                    filewriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Dataset path //
        String proyectoNominalDataset = "wkfile.arff";
        // Create bufferedreader to read the dataset //
        BufferedReader bufferedReader = new BufferedReader(new FileReader(proyectoNominalDataset));

        // Create dataset instances //
        Instances datasetInstances = new Instances(bufferedReader);
        //addStatistisNaiveBayes(datasetInstances);
        //addStatistisLinearRegretion(datasetInstances); //solo numeros
        addStatistisJ48(datasetInstances);
        ///////////////////////////////////////////7

        //////////////////////////////////////////////

        Optional<Proyecto> op = Optional.of(dataProyecto);
        return op.map(proyectoMapper::toDto);
        //////////////////////////////////////77

    }

    private void addStatistisNaiveBayes(Instances instances) throws Exception {
        // Create naivebayes classifier //
        NaiveBayes naivebayes = new NaiveBayes();

        // Divide dataset into training and test data //
        int trainingDataSize = (int) Math.round(instances.numInstances() * 0.66);
        int testDataSize = (int) instances.numInstances() - trainingDataSize;
        // Create training data //
        Instances trainingInstances = new Instances(instances, 0, trainingDataSize);
        // Create test data //
        Instances testInstances = new Instances(instances, trainingDataSize, testDataSize);
        // Set Target class //
        trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
        testInstances.setClassIndex(testInstances.numAttributes() - 1);
        // Build Classifier //
        naivebayes.buildClassifier(trainingInstances);
        // Evaluation //
        Evaluation evaluation = new Evaluation(trainingInstances);
        evaluation.evaluateModel(naivebayes, testInstances);

        double label = naivebayes.classifyInstance(testInstances.instance(0));
        testInstances.instance(0).setClassValue(label);

        System.out.println(evaluation.toSummaryString("\nResults", false));
        //System.out.println(evaluation.KBInformation());
        System.out.println(naivebayes);
        System.out.println(evaluation.toMatrixString());
        //System.out.println(testInstances.instance(0).stringValue(1));

        //System.out.println(evaluation.toSummaryString("\nResults", false));
        /*
        //https://www.geeksforgeeks.org/building-naive-bayesian-classifier-with-weka/
        // Create naivebayes classifier //
        NaiveBayes naivebayes = new NaiveBayes();
        String weatherNominalDataset = "wkfile.arff";
        // Create bufferedreader to read the dataset //
        BufferedReader bufferedReader = new BufferedReader(new FileReader(weatherNominalDataset));
        // Create dataset instances //
        Instances datasetInstances = new Instances(bufferedReader);
        // Randomize the dataset //
        datasetInstances.randomize(new java.util.Random(0));
        // Divide dataset into training and test data //
        int trainingDataSize = (int) Math.round(datasetInstances.numInstances() * 0.66);
        int testDataSize = (int) datasetInstances.numInstances() - trainingDataSize;
        // Create training data //
        Instances trainingInstances = new Instances(datasetInstances, 0, trainingDataSize);
        // Create test data //
        Instances testInstances = new Instances(datasetInstances, trainingDataSize, testDataSize);
        // Set Target class //
        trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
        testInstances.setClassIndex(testInstances.numAttributes() - 1);
        // Close BufferedReader //
        bufferedReader.close();
        // Build Classifier //
        naivebayes.buildClassifier(trainingInstances);

        // Evaluation //
        Evaluation evaluation = new Evaluation(trainingInstances);
        evaluation.evaluateModel(naivebayes, testInstances);
        System.out.println(evaluation.toSummaryString("\nResults", false));
        //System.out.println(evaluation.KBInformation());
        System.out.println(naivebayes);
        System.out.println(evaluation.toMatrixString());
        */
    }

    private void addStatistisLinearRegretion(Instances instances) throws Exception {
        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(instances);
        //System.out.println(lr);
        Evaluation lreval = new Evaluation(instances);
        lreval.evaluateModel(lr, instances);
        System.out.println(lreval.toSummaryString());

        SMOreg smoreg = new SMOreg();
        smoreg.buildClassifier(instances);
        Evaluation svmregeval = new Evaluation(instances);
        svmregeval.evaluateModel(smoreg, instances);
        System.out.println(svmregeval.toSummaryString());
    }

    //https://pocketstudyblog.wordpress.com/2018/10/30/simple-naive-bayes-classification-using-weka-api-in-java-j48-example/
    private void addStatistisJ48(Instances instances) throws Exception {
        Instances train_data = new Instances(instances);
        //Set the class index to the last attribute – i.e. Class value
        train_data.setClassIndex(train_data.numAttributes() - 1);
        String[] options = { "-C", "0.1", "-M", "5" };

        //Build the classifier with the specified options and training data
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(train_data);
        //Perform 5 fold Cross-validation of the model
        Evaluation eval = new Evaluation(train_data);
        eval.crossValidateModel(tree, train_data, 7, new Random(1));
        //Print the summary of the evaluation
        System.out.println(eval.toSummaryString("\n\n Results-J48 DT Algorithm —\n\n", true));
        //Print the other performance parameters
        System.out.println("F1 Measure = " + eval.fMeasure(1));
        System.out.println("Precision=" + eval.precision(1));
        System.out.println("Recall=" + eval.recall(1));
        System.out.println("TNR=" + eval.trueNegativeRate(1));
        System.out.println("TPR=" + eval.truePositiveRate(1));
        System.out.println("FNR=" + eval.falseNegativeRate(1));
        System.out.println("FPR=" + eval.falsePositiveRate(1));

        System.out.println("\n\n" + tree.graph());
        //eval.evaluateModel(tree, train_data);
        //System.out.println("evaluacion de evaluateMode");
        //System.out.println(eval.toMatrixString());
        //System.out.println(eval.predictions());
        //Classifier cls = new J48();
        //cls.buildClassifier(train_data);
        //Evaluation eval = new Evaluation(train_data);
        /*
        Instances train_data = new Instances(instances);
        //Set the class index to the last attribute – i.e. Class value
        train_data.setClassIndex(train_data.numAttributes() - 1);
        String[] options = { "-C", "0.1", "-M", "2" };

        //Build the classifier with the specified options and training data
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(train_data);
        //Perform 10 fold Cross-validation of the model
        Evaluation eval = new Evaluation(train_data);
        eval.crossValidateModel(tree, train_data, 5, new Random(1));
        log.debug(instances.toString());
        log.info(tree.toString());
        //int result = (int) tree.classifyInstance(getInstance(instances));

        //log.info("Resultado de clasificar la nueva instancia: " + result);
        //dataWeather.setAction(result != 0);

        //addStatistis(instances);
*/
    }

    private Instance getInstance(Instances instances) throws Exception {
        NominalToString nominalToBinary = new NominalToString();
        nominalToBinary.setInputFormat(instances);
        // String[] options = { "-C", "1-2-3" };
        //String[] options = { "-C", "0.1", "-M", "2" };

        //nominalToBinary.setOptions(options);
        Instances newInstances = Filter.useFilter(instances, nominalToBinary);
        Instance instance = new DenseInstance(2);
        instance.setDataset(newInstances);
        //instance.setDataset(instances);
        instance.setValue(0, "Ingeniería de Sistemas");
        instance.setValue(1, "aplicada");

        return instances.get(0);
    }

    private String getTime(LocalDateTime fecha) {
        return fecha.getHour() + ":" + fecha.getMinute();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProyectoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proyectos");
        return proyectoRepository.findAll(pageable).map(proyectoMapper::toDto);
    }
}
