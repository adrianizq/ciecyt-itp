package co.edu.itp.ciecyt.service.impl;

import co.edu.itp.ciecyt.domain.Proyecto;
import co.edu.itp.ciecyt.repository.ProyectoRepository;
import co.edu.itp.ciecyt.service.IntegranteProyectoService;
import co.edu.itp.ciecyt.service.ProyectoPredictService;
import co.edu.itp.ciecyt.service.RolesModalidadService;
import co.edu.itp.ciecyt.service.dto.ProyectoDTO;
import co.edu.itp.ciecyt.service.mapper.ProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.Inheritance;


@Service
@Transactional
public class ProyectoPreditcServiceImpl implements ProyectoPredictService {

    private final Logger log = LoggerFactory.getLogger(ProyectoPreditcServiceImpl.class);

    private final ProyectoRepository proyectoRepository;
    private final IntegranteProyectoService integranteProyectoService;
    //private final IntegranteProyectoRepository integranteProyectoRepository;
    private final RolesModalidadService rolesModalidadService;

    private final ProyectoMapper proyectoMapper;
     private DataSourceProperties dataSourceProperties;

    public ProyectoPreditcServiceImpl(
        ProyectoRepository proyectoRepository,
        ProyectoMapper proyectoMapper,
        IntegranteProyectoService integranteProyectoService,
        RolesModalidadService rolesModalidadService,
        DataSourceProperties dataSourceProperties
    ) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.integranteProyectoService = integranteProyectoService;
        this.rolesModalidadService = rolesModalidadService;
        this.dataSourceProperties = dataSourceProperties;
    }



    public  Optional<ProyectoDTO> predicePlay(Long facultad, Long modalidad) throws Exception {


       

        //Attribute attr1 = new Attribute("att-nominal_1", new ArrayList<String>(Arrays.asList(new String[]{"1", "2", "3"})));
       // Attribute attr2 = new Attribute("menopause");
       // Attribute attr3 = new Attribute("tumor-size");
       // Attribute attr4 = new Attribute("inv-nodes");

        //ArrayList<Attribute> atts = new ArrayList<Attribute>();

        //ArrayList<String> classVal = new ArrayList<String>();
        //classVal.add("A");
        //classVal.add("B");
        //atts.add(new Attribute("content",(ArrayList<String>)null));
        //atts.add(new Attribute("@@class@@",classVal));

        //Instances dataRaw = new Instances("Test dataset", atts, 0);
        //atts.add(new Attribute("content",(ArrayList<String>)null));
        //atts.add(attr1);
        //atts.add(attr2);
        //atts.add(attr3);
        //atts.add(attr4);



Proyecto dataProyecto = new Proyecto();

List<Proyecto> lDataProyecto = proyectoRepository.findByFacultadIdAndProyectoModalidadId(facultad,modalidad);


        

ArrayList<Attribute> atts = new ArrayList<Attribute>(2);
        ArrayList<String> classVal = new ArrayList<String>();
        classVal.add("A");
        classVal.add("B");
        classVal.add("C");
        
        atts.add(new Attribute("content",(ArrayList<String>)null));
        atts.add(new Attribute("content2",(ArrayList<String>)null));
        atts.add(new Attribute("@@class@@",classVal));
        Instances dataRaw = new Instances("TestInstances",atts,0);

        for(Proyecto p: lDataProyecto ){
            //double[] instanceValue = new double[dataRaw.numAttributes()];
            double[] instanceValue = new double[3];
           

            //atts.add(new Attribute("content",(ArrayList<String>)null));
        //atts.add(new Attribute("@@class@@",classVal));

            //instanceValue[0] = dataRaw.attribute(0).addStringValue("This is second string!");
            instanceValue[0] = dataRaw.attribute(0).addStringValue(p.getPrograma());
            instanceValue[1] = dataRaw.attribute(1).addStringValue(p.getTitulo());
            
            //instanceValue[1] = p.getProyectoModalidad().getId();
            instanceValue[2] = 0;
            //instanceValue[2] = p.getNota();
            //instanceValue[3] = p.getFacultad().getId();
            dataRaw.add(new DenseInstance(1.0, instanceValue));

        }




        
/*
        IBk ibk = new IBk();
        try {
            ibk.buildClassifier(dataRaw);
            double[] values = new double[]{3,1,0,0,-1};
            DenseInstance newInst = new DenseInstance(1.0,values);
            double classif = ibk.classifyInstance(newInst);
            System.out.println(classif);
        } catch (Exception e) {
            e.printStackTrace();
        }

 */
        /////////////////////////////
        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataRaw);
        saver.setFile(new File("wnew.arff"));
        saver.writeBatch();
        Optional<Proyecto> op = Optional.of(dataProyecto);
        return op.map(proyectoMapper::toDto);
    }


    private void addStatistis(Instances instances) throws Exception {
        Classifier treeClassifier = new J48();
        Evaluation treeEvaluation = new Evaluation(instances);
        int numFolds = instances.size() / 3;
        Debug.Random random = new Debug.Random(1);
        treeEvaluation.crossValidateModel(treeClassifier, instances, numFolds, random);
        log.info(treeEvaluation.toSummaryString());
        log.info(treeEvaluation.toMatrixString());
    }


    private String getTime(LocalDateTime fecha) {
        return fecha.getHour()+":"+fecha.getMinute();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ProyectoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proyectos");
        return proyectoRepository.findAll(pageable).map(proyectoMapper::toDto);
    }


    
}
