package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.Facultad;
import co.edu.itp.ciecyt.domain.Modalidad;
import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.*;
import co.edu.itp.ciecyt.service.dto.InvestigacionTipoDTO;
import co.edu.itp.ciecyt.service.dto.ProgramaDTO;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private FacultadService facultadService;

    private ProgramaService programaService;

    public ProyectoPreditcServiceImpl(
        ProyectoRepository proyectoRepository,
        ProyectoMapper proyectoMapper,
        IntegranteProyectoService integranteProyectoService,
        RolesModalidadService rolesModalidadService,
        DataSourceProperties dataSourceProperties,
        InvestigacionTipoService investigacionTipoService,
        ProgramaService programaService,
        ModalidadService modalidadService,
        FacultadService facultadService
    ) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.integranteProyectoService = integranteProyectoService;
        this.rolesModalidadService = rolesModalidadService;
        this.dataSourceProperties = dataSourceProperties;
        this.investigacionTipoService = investigacionTipoService;
        this.programaService = programaService;
        this.modalidadService = modalidadService;
        this.facultadService = facultadService;
    }

    public Optional<String> predicePlay(Long facultad, Long modalidad) throws Exception {
        //traer todos los registros de programa
        String estadisticas = new String();
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

        //traer todos los registros de facultad
        List<Facultad> facultads;
        String facultadCad = "";
        facultads = facultadService.buscarAll();
        List<String> facultades = new ArrayList<String>();
        for (Facultad fa : facultads) {
            facultades.add(fa.getFacultad());
            facultadCad += "'" + fa.getFacultad() + "',";
            System.out.println(fa.getFacultad());
        }
        facultadCad = facultadCad.substring(0, facultadCad.length() - 1);

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
            info += p.getFacultad().getFacultad();
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
            "@attribute facultad {" +
            facultadCad +
            "}" +
            "\n" +
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
        estadisticas = addStatistisNaiveBayes(datasetInstances);

        //Optional<String> op = Optional.of(dataProyecto);
        //return op.map(proyectoMapper::toDto);
         Optional<String> op = Optional.of(estadisticas);
        return op;

    }

    private String addStatistisNaiveBayes(Instances instances) throws Exception {
        String estadisticas= new String();
        // Create naivebayes classifier //
        //https://pocketstudyblog.wordpress.com/2018/10/30/simple-naive-bayes-classification-using-weka-api-in-java/

        //Load the instances
        Instances train_data = instances;
        //Set the class index to the last attribute – i.e. Class value
        train_data.setClassIndex(train_data.numAttributes() - 1);

        //Load the Naive Bayes classifier
        NaiveBayes nb = new NaiveBayes();
        //Build the classifier with the training data
        nb.buildClassifier(train_data);

        //Perform 10 fold Cross-validation of the model
        Evaluation eval = new Evaluation(train_data);
        eval.crossValidateModel(nb, train_data, 10, new Random(1));

        //Print the summary of the evaluation
        System.out.println(eval.toSummaryString("\n\n Results \n\n", true));

        //Print the other performance parameters
        System.out.println("F1 Measure = " + eval.fMeasure(0));
        System.out.println("Precision=" + eval.precision(0));
        System.out.println("Recall=" + eval.recall(0));
        System.out.println("TNR=" + eval.trueNegativeRate(0));
        System.out.println("TPR=" + eval.truePositiveRate(0));
        System.out.println("FNR=" + eval.falseNegativeRate(0));
        System.out.println("FPR=" + eval.falsePositiveRate(0));
        System.out.println("Matrix=" + eval.toMatrixString());

        estadisticas+= "F1 Measure = " + eval.fMeasure(0) + "\n";
        estadisticas+= "Precision=" + eval.precision(0)+ "\n";
        estadisticas+= "Recall=" + eval.recall(0)+ "\n";
        estadisticas+= "TNR=" + eval.trueNegativeRate(0)+ "\n";
        estadisticas+= "TPR=" + eval.truePositiveRate(0)+ "\n";
        estadisticas+= "FNR=" + eval.falseNegativeRate(0)+ "\n";
        estadisticas+= "FPR=" + eval.falsePositiveRate(0)+ "\n";
        estadisticas+= "Matrix=" + eval.toMatrixString()+ "\n";
        return estadisticas;
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
        System.out.println("Matrix=" + eval.toMatrixString());

        System.out.println("\n\n" + tree.graph());
    }

    // private Instance getInstance(Instances instances, Weather dataWeather) throws Exception {
    private Instance getInstance(Instances instances) throws Exception {
        NominalToString nominalToBinary = new NominalToString();
        nominalToBinary.setInputFormat(instances);
        String[] options = { "-C", "1-2-3" };
        nominalToBinary.setOptions(options);
        Instances newInstances = Filter.useFilter(instances, nominalToBinary);
        Instance instance = new DenseInstance(4);
        instance.setDataset(newInstances);
        instance.setValue(0, "Ingeniería de Sistemas");
        instance.setValue(1, "Tesis");
        instance.setValue(2, "Ingeniería y Ciencias Básicas");
        instance.setValue(3, "Teorica");
        return instance;
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
