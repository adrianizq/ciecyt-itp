PGDMP         4                x         	   ciecyt_db    9.5.23    12.3 �    
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            	
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            

           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            
           1262    16385 	   ciecyt_db    DATABASE     {   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_CO.UTF-8' LC_CTYPE = 'es_CO.UTF-8';
    DROP DATABASE ciecyt_db;
                ciecyt_admin    false            
           0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    16482    acuerdo    TABLE     �   CREATE TABLE public.acuerdo (
    id bigint NOT NULL,
    acuerdo character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.acuerdo;
       public            ciecyt_admin    false            �            1259    16643    adjunto_proyecto_fase    TABLE     �  CREATE TABLE public.adjunto_proyecto_fase (
    id bigint NOT NULL,
    nombre_adjunto character varying(255),
    fecha_creacion date,
    fecha_modificacion date,
    estado_adjunto integer,
    adjunto_proyecto_fase character varying(255),
    nombre_archivo_original character varying(255),
    fecha_inicio date,
    fecha_fin date,
    adjunto_proyecto_fase_proyecto_fase_id bigint
);
 )   DROP TABLE public.adjunto_proyecto_fase;
       public            ciecyt_admin    false            �            1259    16659    adjunto_retroalimentacion    TABLE     �  CREATE TABLE public.adjunto_retroalimentacion (
    id bigint NOT NULL,
    nombre_adjunto character varying(255),
    fecha_creacion date,
    fecha_modificacion date,
    estado_adjunto integer,
    adjunto_retroalimentacion character varying(255),
    nombre_archivo_original character varying(255),
    fecha_inicio date,
    fecha_fin date,
    adjunto_retroalimentacion_retroalimentacion_id bigint
);
 -   DROP TABLE public.adjunto_retroalimentacion;
       public            ciecyt_admin    false            �            1259    16675    categorizacion    TABLE     �   CREATE TABLE public.categorizacion (
    id bigint NOT NULL,
    categoria character varying(255),
    descripcion character varying(255),
    categorizacion_proyecto_id bigint
);
 "   DROP TABLE public.categorizacion;
       public            ciecyt_admin    false            �            1259    16490    ciclo_propedeutico    TABLE     �   CREATE TABLE public.ciclo_propedeutico (
    id bigint NOT NULL,
    ciclo character varying(255),
    modalidad_id bigint,
    ciclo_propedeutico_acuerdo_id bigint
);
 &   DROP TABLE public.ciclo_propedeutico;
       public            ciecyt_admin    false            �            1259    16521 
   cronograma    TABLE     �   CREATE TABLE public.cronograma (
    id bigint NOT NULL,
    actividad character varying(255),
    duracion integer,
    fecha_inicio date,
    fecha_fin date,
    cronograma_proyecto_id bigint,
    orden_vista integer
);
    DROP TABLE public.cronograma;
       public            ciecyt_admin    false            �            1259    16612    cronograma_ciecyt    TABLE     �   CREATE TABLE public.cronograma_ciecyt (
    id bigint NOT NULL,
    titulo_cronograma character varying(255),
    fecha_inicio date,
    fecha_fin date,
    observaciones character varying(255),
    cronograma_ciecyt_modalidad_id bigint
);
 %   DROP TABLE public.cronograma_ciecyt;
       public            ciecyt_admin    false            �            1259    16620    cronograma_ciecyt_fases    TABLE       CREATE TABLE public.cronograma_ciecyt_fases (
    id bigint NOT NULL,
    inicio_fase date,
    fin_fase date,
    texto_explicativo character varying(255),
    cronograma_ciecyt_fases_cronograma_ciecyt_id bigint,
    cronograma_ciecyt_fases_fases_id bigint
);
 +   DROP TABLE public.cronograma_ciecyt_fases;
       public            ciecyt_admin    false            �            1259    16391    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);
 %   DROP TABLE public.databasechangelog;
       public            ciecyt_admin    false            �            1259    16386    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public            ciecyt_admin    false            �            1259    16552    elemento    TABLE     �   CREATE TABLE public.elemento (
    id bigint NOT NULL,
    elemento character varying(255),
    elemento_formato_id bigint,
    elemento_modalidad_id bigint,
    descripcion text
);
    DROP TABLE public.elemento;
       public            ciecyt_admin    false            �            1259    16557    elemento_proyecto    TABLE     �   CREATE TABLE public.elemento_proyecto (
    id bigint NOT NULL,
    dato text,
    elemento_proyecto_elemento_id bigint,
    elemento_proyecto_proyecto_id bigint,
    elemento_proyecto_proyecto_descripcion text
);
 %   DROP TABLE public.elemento_proyecto;
       public            ciecyt_admin    false            �            1259    16539    entidad    TABLE     |   CREATE TABLE public.entidad (
    id bigint NOT NULL,
    entidad character varying(255),
    nit character varying(255)
);
    DROP TABLE public.entidad;
       public            ciecyt_admin    false            �            1259    16547    entidad_financiadora    TABLE     �   CREATE TABLE public.entidad_financiadora (
    id bigint NOT NULL,
    valor double precision,
    aprobada boolean,
    entidad_financiadora_entidad_id bigint,
    entidad_financiadora_proyecto_id bigint
);
 (   DROP TABLE public.entidad_financiadora;
       public            ciecyt_admin    false            �            1259    16469    facultad    TABLE     �   CREATE TABLE public.facultad (
    id bigint NOT NULL,
    codigo_facultad character varying(255),
    facultad character varying(255)
);
    DROP TABLE public.facultad;
       public            ciecyt_admin    false            �            1259    16599    fases    TABLE     �   CREATE TABLE public.fases (
    id bigint NOT NULL,
    fase character varying(255),
    notificable boolean,
    fases_modalidad_id bigint
);
    DROP TABLE public.fases;
       public            ciecyt_admin    false            �            1259    16667    ficha_tecnica    TABLE     �   CREATE TABLE public.ficha_tecnica (
    id bigint NOT NULL,
    titulo_profesional character varying(255),
    titulo_postgrado character varying(255),
    experiencia character varying(255),
    ficha_tecnica_user_id bigint
);
 !   DROP TABLE public.ficha_tecnica;
       public            ciecyt_admin    false            �            1259    16562    formato    TABLE     �   CREATE TABLE public.formato (
    id bigint NOT NULL,
    formato character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.formato;
       public            ciecyt_admin    false            �            1259    16464    grupo_semillero    TABLE     u   CREATE TABLE public.grupo_semillero (
    id bigint NOT NULL,
    nombre character varying(255),
    tipo boolean
);
 #   DROP TABLE public.grupo_semillero;
       public            ciecyt_admin    false            �            1259    16513    impactos_esperados    TABLE     	  CREATE TABLE public.impactos_esperados (
    id bigint NOT NULL,
    impacto character varying(255),
    plazo integer,
    indicador character varying(255),
    supuestos character varying(255),
    impactos_esperado_proyecto_id bigint,
    orden_vista integer
);
 &   DROP TABLE public.impactos_esperados;
       public            ciecyt_admin    false            �            1259    41007    informacion_pasantia    TABLE     �  CREATE TABLE public.informacion_pasantia (
    duracion_horas integer,
    direccion text,
    email text,
    lunes boolean,
    martes boolean,
    miercoles boolean,
    jueves boolean,
    viernes boolean,
    sabado boolean,
    domingo boolean,
    horas_mes integer,
    bono_alimenticio boolean,
    apoyo_economico boolean,
    auxilio_transporte boolean,
    capacitacion boolean,
    otro_apoyo text,
    nombre_empresa text,
    nit_empresa text,
    direccion_empresa text,
    sector_economico_empresa text,
    representante_legal_empresa text,
    asesor_empresa text,
    cargo_asesor_empresa text,
    email_asesor_empresa text,
    municipio_empresa text,
    telefono_contacto_empresa text,
    email_empresa text,
    departamento_empresa text,
    identificacion_representante_legal text,
    profesion_asesor_empresa text,
    celular_asesor_empresa text,
    id bigint NOT NULL,
    informacion_pasantia_proyecto_id bigint
);
 (   DROP TABLE public.informacion_pasantia;
       public            ciecyt_admin    false            �            1259    16625    integrante_proyecto    TABLE       CREATE TABLE public.integrante_proyecto (
    id bigint NOT NULL,
    integrante character varying(255),
    descripcion character varying(255),
    integrante_proyecto_user_id bigint,
    integrante_proyecto_proyecto_id bigint,
    integrante_proyecto_roles_modalidad_id bigint
);
 '   DROP TABLE public.integrante_proyecto;
       public            ciecyt_admin    false            �            1259    16411    jhi_authority    TABLE     O   CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);
 !   DROP TABLE public.jhi_authority;
       public            ciecyt_admin    false            �            1259    16431    jhi_persistent_audit_event    TABLE     �   CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);
 .   DROP TABLE public.jhi_persistent_audit_event;
       public            ciecyt_admin    false            �            1259    16436    jhi_persistent_audit_evt_data    TABLE     �   CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);
 1   DROP TABLE public.jhi_persistent_audit_evt_data;
       public            ciecyt_admin    false            �            1259    16399    jhi_user    TABLE     �  CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(191),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(10),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);
    DROP TABLE public.jhi_user;
       public            ciecyt_admin    false            �            1259    16416    jhi_user_authority    TABLE     {   CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);
 &   DROP TABLE public.jhi_user_authority;
       public            ciecyt_admin    false            �            1259    16456    linea_investigacion    TABLE     �   CREATE TABLE public.linea_investigacion (
    id bigint NOT NULL,
    linea character varying(255),
    codigo_linea character varying(255),
    linea_padre_id bigint,
    linea_investigacion_facultad_id bigint
);
 '   DROP TABLE public.linea_investigacion;
       public            ciecyt_admin    false            �            1259    16691    menu    TABLE     �   CREATE TABLE public.menu (
    id bigint NOT NULL,
    nombre character varying(255),
    url character varying(255),
    icono character varying(255),
    activo boolean,
    menu_padre_id bigint,
    es_publico boolean,
    orden integer
);
    DROP TABLE public.menu;
       public            ciecyt_admin    false            �            1259    16477 	   modalidad    TABLE     �   CREATE TABLE public.modalidad (
    id bigint NOT NULL,
    modalidad character varying(255),
    modalidad_acuerdo_id bigint
);
    DROP TABLE public.modalidad;
       public            ciecyt_admin    false            �            1259    16578    pregunta    TABLE     H  CREATE TABLE public.pregunta (
    id bigint NOT NULL,
    encabezado character varying(255),
    descripcion character varying(255),
    pregunta character varying(255),
    pregunta_tipo_pregunta_id bigint,
    pregunta_modalidad_id bigint,
    pregunta_roles_modalidad_id bigint,
    elemento text,
    elemento_id bigint
);
    DROP TABLE public.pregunta;
       public            ciecyt_admin    false            �            1259    16531    presupuesto_valor    TABLE     �  CREATE TABLE public.presupuesto_valor (
    id bigint NOT NULL,
    descripcion character varying(255),
    justificacion character varying(255),
    cantidad integer,
    valor_unitario double precision,
    especie double precision,
    dinero double precision,
    presupuesto_valor_rubro_id bigint,
    presupuesto_valor_proyecto_id bigint,
    presupuesto_valor_entidad_id bigint,
    orden_vista integer
);
 %   DROP TABLE public.presupuesto_valor;
       public            ciecyt_admin    false            �            1259    16503    producto    TABLE     ^   CREATE TABLE public.producto (
    id bigint NOT NULL,
    producto character varying(255)
);
    DROP TABLE public.producto;
       public            ciecyt_admin    false            �            1259    16508    producto_proyecto    TABLE     �   CREATE TABLE public.producto_proyecto (
    id bigint NOT NULL,
    aplica boolean,
    descripcion character varying(255),
    producto_proyecto_producto_id bigint,
    producto_proyecto_proyecto_id bigint
);
 %   DROP TABLE public.producto_proyecto;
       public            ciecyt_admin    false            �            1259    16448    proyecto    TABLE     m  CREATE TABLE public.proyecto (
    id bigint NOT NULL,
    titulo character varying(255),
    url character varying(255),
    lugar_ejecucion character varying(255),
    duracion character varying(255),
    fecha_ini date,
    fecha_fin date,
    contrapartida_pesos double precision,
    contrapartida_especie double precision,
    palabras_clave text,
    convocatoria character varying(255),
    proyecto_linea_investigacion_id bigint,
    proyecto_grupo_semillero_id bigint,
    proyecto_modalidad_id bigint,
    facultad_id bigint,
    sub_linea_linea_investigacion_id bigint,
    tipo text,
    referencias text
);
    DROP TABLE public.proyecto;
       public            ciecyt_admin    false            �            1259    16604    proyecto_fase    TABLE       CREATE TABLE public.proyecto_fase (
    id bigint NOT NULL,
    titulo character varying(255),
    cumplida boolean,
    fecha_cumplimiento date,
    observaciones character varying(255),
    proyecto_fase_fases_id bigint,
    proyecto_fase_proyecto_id bigint
);
 !   DROP TABLE public.proyecto_fase;
       public            ciecyt_admin    false            �            1259    16586    proyecto_respuestas    TABLE        CREATE TABLE public.proyecto_respuestas (
    id bigint NOT NULL,
    respuesta character varying(255),
    observaciones character varying(255),
    viable boolean,
    proyecto_respuestas_pregunta_id bigint,
    proyecto_respuestas_proyecto_id bigint
);
 '   DROP TABLE public.proyecto_respuestas;
       public            ciecyt_admin    false            �            1259    16495    resultados_esperados    TABLE        CREATE TABLE public.resultados_esperados (
    id bigint NOT NULL,
    resultado character varying(255),
    indicador character varying(255),
    beneficiario character varying(255),
    resultados_esperados_proyecto_id bigint,
    orden_vista integer
);
 (   DROP TABLE public.resultados_esperados;
       public            ciecyt_admin    false            �            1259    16651    retroalimentacion    TABLE     O  CREATE TABLE public.retroalimentacion (
    id bigint NOT NULL,
    titulo character varying(255),
    retroalimentacion character varying(255),
    fecha_retroalimentacion date,
    estado_retroalimentacion date,
    estado_proyecto_fase integer,
    retroalimentacion_proyecto_fase_id bigint,
    retroalimentacion_user_id bigint
);
 %   DROP TABLE public.retroalimentacion;
       public            ciecyt_admin    false            �            1259    16699    rol_menu    TABLE     �   CREATE TABLE public.rol_menu (
    id bigint NOT NULL,
    permitir_acceso boolean,
    permitir_crear boolean,
    permitir_editar boolean,
    permitir_eliminar boolean,
    auth_name character varying(255),
    rol_menu_menu_id bigint
);
    DROP TABLE public.rol_menu;
       public            ciecyt_admin    false            �            1259    16594    roles_modalidad    TABLE     �   CREATE TABLE public.roles_modalidad (
    id bigint NOT NULL,
    rol character varying(255),
    cantidad integer,
    calificador boolean,
    roles_modalidad_modalidad_id bigint
);
 #   DROP TABLE public.roles_modalidad;
       public            ciecyt_admin    false            �            1259    16526    rubro    TABLE     X   CREATE TABLE public.rubro (
    id bigint NOT NULL,
    rubro character varying(255)
);
    DROP TABLE public.rubro;
       public            ciecyt_admin    false            �            1259    16397    sequence_generator    SEQUENCE        CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sequence_generator;
       public          ciecyt_admin    false            �            1259    16634 	   solicitud    TABLE     �   CREATE TABLE public.solicitud (
    id bigint NOT NULL,
    estado boolean,
    asunto character varying(255),
    texto_solicitud character varying(255),
    fecha_solicitud date,
    solicitud_integrante_proyecto_id bigint
);
    DROP TABLE public.solicitud;
       public            ciecyt_admin    false            �            1259    16570    tipo_pregunta    TABLE     �   CREATE TABLE public.tipo_pregunta (
    id bigint NOT NULL,
    tipo_pregunta character varying(255),
    tipo_dato character varying(255)
);
 !   DROP TABLE public.tipo_pregunta;
       public            ciecyt_admin    false            �            1259    16683    usuario    TABLE     �   CREATE TABLE public.usuario (
    id bigint NOT NULL,
    usuario character varying(255),
    descripcion character varying(255)
);
    DROP TABLE public.usuario;
       public            ciecyt_admin    false            �	          0    16482    acuerdo 
   TABLE DATA           F   COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM stdin;
    public          ciecyt_admin    false    194   �#      �	          0    16643    adjunto_proyecto_fase 
   TABLE DATA           �   COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM stdin;
    public          ciecyt_admin    false    218   ?$      �	          0    16659    adjunto_retroalimentacion 
   TABLE DATA           �   COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM stdin;
    public          ciecyt_admin    false    220   M&      
          0    16675    categorizacion 
   TABLE DATA           `   COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    222   c(      �	          0    16490    ciclo_propedeutico 
   TABLE DATA           d   COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM stdin;
    public          ciecyt_admin    false    195   �)      �	          0    16521 
   cronograma 
   TABLE DATA           {   COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id, orden_vista) FROM stdin;
    public          ciecyt_admin    false    200   �)      �	          0    16612    cronograma_ciecyt 
   TABLE DATA           �   COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM stdin;
    public          ciecyt_admin    false    214   A*      �	          0    16620    cronograma_ciecyt_fases 
   TABLE DATA           �   COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM stdin;
    public          ciecyt_admin    false    215   �+      �	          0    16391    databasechangelog 
   TABLE DATA           �   COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    public          ciecyt_admin    false    182   �,      �	          0    16386    databasechangeloglock 
   TABLE DATA           R   COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    public          ciecyt_admin    false    181   �C      �	          0    16552    elemento 
   TABLE DATA           i   COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM stdin;
    public          ciecyt_admin    false    205   �C      �	          0    16557    elemento_proyecto 
   TABLE DATA           �   COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id, elemento_proyecto_proyecto_descripcion) FROM stdin;
    public          ciecyt_admin    false    206   fS      �	          0    16539    entidad 
   TABLE DATA           3   COPY public.entidad (id, entidad, nit) FROM stdin;
    public          ciecyt_admin    false    203   .z      �	          0    16547    entidad_financiadora 
   TABLE DATA           �   COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id, entidad_financiadora_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    204   X{      �	          0    16469    facultad 
   TABLE DATA           A   COPY public.facultad (id, codigo_facultad, facultad) FROM stdin;
    public          ciecyt_admin    false    192   �{      �	          0    16599    fases 
   TABLE DATA           J   COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM stdin;
    public          ciecyt_admin    false    212   |       
          0    16667    ficha_tecnica 
   TABLE DATA           u   COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM stdin;
    public          ciecyt_admin    false    221   
}      �	          0    16562    formato 
   TABLE DATA           F   COPY public.formato (id, formato, version, codigo, fecha) FROM stdin;
    public          ciecyt_admin    false    207   �~      �	          0    16464    grupo_semillero 
   TABLE DATA           ;   COPY public.grupo_semillero (id, nombre, tipo) FROM stdin;
    public          ciecyt_admin    false    191   F      �	          0    16513    impactos_esperados 
   TABLE DATA           �   COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id, orden_vista) FROM stdin;
    public          ciecyt_admin    false    199   �      
          0    41007    informacion_pasantia 
   TABLE DATA           Z  COPY public.informacion_pasantia (duracion_horas, direccion, email, lunes, martes, miercoles, jueves, viernes, sabado, domingo, horas_mes, bono_alimenticio, apoyo_economico, auxilio_transporte, capacitacion, otro_apoyo, nombre_empresa, nit_empresa, direccion_empresa, sector_economico_empresa, representante_legal_empresa, asesor_empresa, cargo_asesor_empresa, email_asesor_empresa, municipio_empresa, telefono_contacto_empresa, email_empresa, departamento_empresa, identificacion_representante_legal, profesion_asesor_empresa, celular_asesor_empresa, id, informacion_pasantia_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    226   �      �	          0    16625    integrante_proyecto 
   TABLE DATA           �   COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM stdin;
    public          ciecyt_admin    false    216   ��      �	          0    16411    jhi_authority 
   TABLE DATA           -   COPY public.jhi_authority (name) FROM stdin;
    public          ciecyt_admin    false    185   Ƃ      �	          0    16431    jhi_persistent_audit_event 
   TABLE DATA           a   COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
    public          ciecyt_admin    false    187   �      �	          0    16436    jhi_persistent_audit_evt_data 
   TABLE DATA           N   COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
    public          ciecyt_admin    false    188   �      �	          0    16399    jhi_user 
   TABLE DATA           �   COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
    public          ciecyt_admin    false    184   ��      �	          0    16416    jhi_user_authority 
   TABLE DATA           E   COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
    public          ciecyt_admin    false    186   �      �	          0    16456    linea_investigacion 
   TABLE DATA           w   COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_facultad_id) FROM stdin;
    public          ciecyt_admin    false    190   |�      
          0    16691    menu 
   TABLE DATA           `   COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM stdin;
    public          ciecyt_admin    false    224   ʎ      �	          0    16477 	   modalidad 
   TABLE DATA           H   COPY public.modalidad (id, modalidad, modalidad_acuerdo_id) FROM stdin;
    public          ciecyt_admin    false    193   �      �	          0    16578    pregunta 
   TABLE DATA           �   COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, elemento, elemento_id) FROM stdin;
    public          ciecyt_admin    false    209   L�      �	          0    16531    presupuesto_valor 
   TABLE DATA           �   COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id, orden_vista) FROM stdin;
    public          ciecyt_admin    false    202   9�      �	          0    16503    producto 
   TABLE DATA           0   COPY public.producto (id, producto) FROM stdin;
    public          ciecyt_admin    false    197   �      �	          0    16508    producto_proyecto 
   TABLE DATA           �   COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    198   �      �	          0    16448    proyecto 
   TABLE DATA           E  COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias) FROM stdin;
    public          ciecyt_admin    false    189   ��      �	          0    16604    proyecto_fase 
   TABLE DATA           �   COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    213   3�      �	          0    16586    proyecto_respuestas 
   TABLE DATA           �   COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    210   ��      �	          0    16495    resultados_esperados 
   TABLE DATA           �   COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id, orden_vista) FROM stdin;
    public          ciecyt_admin    false    196   ��      �	          0    16651    retroalimentacion 
   TABLE DATA           �   COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM stdin;
    public          ciecyt_admin    false    219   ؟      
          0    16699    rol_menu 
   TABLE DATA           �   COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM stdin;
    public          ciecyt_admin    false    225   t�      �	          0    16594    roles_modalidad 
   TABLE DATA           g   COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id) FROM stdin;
    public          ciecyt_admin    false    211   �      �	          0    16526    rubro 
   TABLE DATA           *   COPY public.rubro (id, rubro) FROM stdin;
    public          ciecyt_admin    false    201   ��      �	          0    16634 	   solicitud 
   TABLE DATA           {   COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM stdin;
    public          ciecyt_admin    false    217   B�      �	          0    16570    tipo_pregunta 
   TABLE DATA           E   COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM stdin;
    public          ciecyt_admin    false    208   ��      
          0    16683    usuario 
   TABLE DATA           ;   COPY public.usuario (id, usuario, descripcion) FROM stdin;
    public          ciecyt_admin    false    223    �      
           0    0    sequence_generator    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sequence_generator', 22800, true);
          public          ciecyt_admin    false    183            �           2606    16489    acuerdo acuerdo_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.acuerdo
    ADD CONSTRAINT acuerdo_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.acuerdo DROP CONSTRAINT acuerdo_pkey;
       public            ciecyt_admin    false    194            $	           2606    16650 0   adjunto_proyecto_fase adjunto_proyecto_fase_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_pkey;
       public            ciecyt_admin    false    218            (	           2606    16666 8   adjunto_retroalimentacion adjunto_retroalimentacion_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_pkey;
       public            ciecyt_admin    false    220            ,	           2606    16682 "   categorizacion categorizacion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT categorizacion_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT categorizacion_pkey;
       public            ciecyt_admin    false    222            �           2606    16494 *   ciclo_propedeutico ciclo_propedeutico_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT ciclo_propedeutico_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT ciclo_propedeutico_pkey;
       public            ciecyt_admin    false    195            	           2606    16624 4   cronograma_ciecyt_fases cronograma_ciecyt_fases_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_pkey;
       public            ciecyt_admin    false    215            	           2606    16619 (   cronograma_ciecyt cronograma_ciecyt_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT cronograma_ciecyt_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT cronograma_ciecyt_pkey;
       public            ciecyt_admin    false    214             	           2606    16525    cronograma cronograma_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT cronograma_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT cronograma_pkey;
       public            ciecyt_admin    false    200            �           2606    16390 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public            ciecyt_admin    false    181            
	           2606    16556    elemento elemento_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT elemento_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.elemento DROP CONSTRAINT elemento_pkey;
       public            ciecyt_admin    false    205            	           2606    16561 (   elemento_proyecto elemento_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT elemento_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT elemento_proyecto_pkey;
       public            ciecyt_admin    false    206            	           2606    16551 .   entidad_financiadora entidad_financiadora_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT entidad_financiadora_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT entidad_financiadora_pkey;
       public            ciecyt_admin    false    204            	           2606    16546    entidad entidad_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.entidad
    ADD CONSTRAINT entidad_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.entidad DROP CONSTRAINT entidad_pkey;
       public            ciecyt_admin    false    203            �           2606    16476    facultad facultad_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.facultad DROP CONSTRAINT facultad_pkey;
       public            ciecyt_admin    false    192            	           2606    16603    fases fases_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fases_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.fases DROP CONSTRAINT fases_pkey;
       public            ciecyt_admin    false    212            *	           2606    16674     ficha_tecnica ficha_tecnica_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT ficha_tecnica_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT ficha_tecnica_pkey;
       public            ciecyt_admin    false    221            	           2606    16569    formato formato_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.formato DROP CONSTRAINT formato_pkey;
       public            ciecyt_admin    false    207            �           2606    16468 $   grupo_semillero grupo_semillero_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.grupo_semillero
    ADD CONSTRAINT grupo_semillero_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.grupo_semillero DROP CONSTRAINT grupo_semillero_pkey;
       public            ciecyt_admin    false    191            �           2606    16520 *   impactos_esperados impactos_esperados_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT impactos_esperados_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT impactos_esperados_pkey;
       public            ciecyt_admin    false    199            4	           2606    41016 .   informacion_pasantia informacion_pasantia_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.informacion_pasantia
    ADD CONSTRAINT informacion_pasantia_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.informacion_pasantia DROP CONSTRAINT informacion_pasantia_pkey;
       public            ciecyt_admin    false    226             	           2606    16632 ,   integrante_proyecto integrante_proyecto_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT integrante_proyecto_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT integrante_proyecto_pkey;
       public            ciecyt_admin    false    216            �           2606    16415     jhi_authority jhi_authority_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);
 J   ALTER TABLE ONLY public.jhi_authority DROP CONSTRAINT jhi_authority_pkey;
       public            ciecyt_admin    false    185            �           2606    16435 :   jhi_persistent_audit_event jhi_persistent_audit_event_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);
 d   ALTER TABLE ONLY public.jhi_persistent_audit_event DROP CONSTRAINT jhi_persistent_audit_event_pkey;
       public            ciecyt_admin    false    187            �           2606    16440 @   jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);
 j   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT jhi_persistent_audit_evt_data_pkey;
       public            ciecyt_admin    false    188    188            �           2606    16420 *   jhi_user_authority jhi_user_authority_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);
 T   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT jhi_user_authority_pkey;
       public            ciecyt_admin    false    186    186            �           2606    16406    jhi_user jhi_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT jhi_user_pkey;
       public            ciecyt_admin    false    184            �           2606    16463 ,   linea_investigacion linea_investigacion_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT linea_investigacion_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT linea_investigacion_pkey;
       public            ciecyt_admin    false    190            0	           2606    16698    menu menu_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public            ciecyt_admin    false    224            �           2606    16481    modalidad modalidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT modalidad_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT modalidad_pkey;
       public            ciecyt_admin    false    193            	           2606    16585    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public            ciecyt_admin    false    209            	           2606    16538 (   presupuesto_valor presupuesto_valor_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT presupuesto_valor_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT presupuesto_valor_pkey;
       public            ciecyt_admin    false    202            �           2606    16507    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public            ciecyt_admin    false    197            �           2606    16512 (   producto_proyecto producto_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT producto_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT producto_proyecto_pkey;
       public            ciecyt_admin    false    198            	           2606    16611     proyecto_fase proyecto_fase_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT proyecto_fase_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT proyecto_fase_pkey;
       public            ciecyt_admin    false    213            �           2606    16455    proyecto proyecto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public            ciecyt_admin    false    189            	           2606    16593 ,   proyecto_respuestas proyecto_respuestas_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT proyecto_respuestas_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT proyecto_respuestas_pkey;
       public            ciecyt_admin    false    210            �           2606    16502 .   resultados_esperados resultados_esperados_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT resultados_esperados_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT resultados_esperados_pkey;
       public            ciecyt_admin    false    196            &	           2606    16658 (   retroalimentacion retroalimentacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT retroalimentacion_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT retroalimentacion_pkey;
       public            ciecyt_admin    false    219            2	           2606    16703    rol_menu rol_menu_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT rol_menu_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT rol_menu_pkey;
       public            ciecyt_admin    false    225            	           2606    16598 $   roles_modalidad roles_modalidad_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT roles_modalidad_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT roles_modalidad_pkey;
       public            ciecyt_admin    false    211            	           2606    16530    rubro rubro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rubro
    ADD CONSTRAINT rubro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rubro DROP CONSTRAINT rubro_pkey;
       public            ciecyt_admin    false    201            "	           2606    16641    solicitud solicitud_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT solicitud_pkey;
       public            ciecyt_admin    false    217            	           2606    16577     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public            ciecyt_admin    false    208            .	           2606    16690    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            ciecyt_admin    false    223            �           2606    16408    jhi_user ux_user_email 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_email;
       public            ciecyt_admin    false    184            �           2606    16410    jhi_user ux_user_login 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_login;
       public            ciecyt_admin    false    184            �           1259    16441    idx_persistent_audit_event    INDEX     r   CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);
 .   DROP INDEX public.idx_persistent_audit_event;
       public            ciecyt_admin    false    187    187            �           1259    16442    idx_persistent_audit_evt_data    INDEX     k   CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);
 1   DROP INDEX public.idx_persistent_audit_evt_data;
       public            ciecyt_admin    false    188            ^	           2606    16894 R   adjunto_proyecto_fase adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id FOREIGN KEY (adjunto_proyecto_fase_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 |   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id;
       public          ciecyt_admin    false    213    218    2330            a	           2606    16909 Y   adjunto_retroalimentacion adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id FOREIGN KEY (adjunto_retroalimentacion_retroalimentacion_id) REFERENCES public.retroalimentacion(id);
 �   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id;
       public          ciecyt_admin    false    2342    220    219            X	           2606    16864 W   cronograma_ciecyt_fases cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id FOREIGN KEY (cronograma_ciecyt_fases_cronograma_ciecyt_id) REFERENCES public.cronograma_ciecyt(id);
 �   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id;
       public          ciecyt_admin    false    2332    215    214            5	           2606    16421 $   jhi_user_authority fk_authority_name    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);
 N   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_authority_name;
       public          ciecyt_admin    false    2272    186    185            c	           2606    16919 ;   categorizacion fk_categorizacion_categorizacion_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT fk_categorizacion_categorizacion_proyecto_id FOREIGN KEY (categorizacion_proyecto_id) REFERENCES public.proyecto(id);
 e   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT fk_categorizacion_categorizacion_proyecto_id;
       public          ciecyt_admin    false    2282    222    189            @	           2606    16744 F   ciclo_propedeutico fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id FOREIGN KEY (ciclo_propedeutico_acuerdo_id) REFERENCES public.acuerdo(id);
 p   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id;
       public          ciecyt_admin    false    195    194    2292            ?	           2606    16739 5   ciclo_propedeutico fk_ciclo_propedeutico_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_modalidad_id FOREIGN KEY (modalidad_id) REFERENCES public.modalidad(id);
 _   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_modalidad_id;
       public          ciecyt_admin    false    195    193    2290            W	           2606    16859 E   cronograma_ciecyt fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id FOREIGN KEY (cronograma_ciecyt_modalidad_id) REFERENCES public.modalidad(id);
 o   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id;
       public          ciecyt_admin    false    214    2290    193            Y	           2606    16869 S   cronograma_ciecyt_fases fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id FOREIGN KEY (cronograma_ciecyt_fases_fases_id) REFERENCES public.fases(id);
 }   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id;
       public          ciecyt_admin    false    2328    215    212            E	           2606    16769 /   cronograma fk_cronograma_cronograma_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT fk_cronograma_cronograma_proyecto_id FOREIGN KEY (cronograma_proyecto_id) REFERENCES public.proyecto(id);
 Y   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT fk_cronograma_cronograma_proyecto_id;
       public          ciecyt_admin    false    189    200    2282            J	           2606    16794 (   elemento fk_elemento_elemento_formato_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_formato_id FOREIGN KEY (elemento_formato_id) REFERENCES public.formato(id);
 R   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_formato_id;
       public          ciecyt_admin    false    207    2318    205            K	           2606    16799 *   elemento fk_elemento_elemento_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_modalidad_id FOREIGN KEY (elemento_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_modalidad_id;
       public          ciecyt_admin    false    205    193    2290            L	           2606    16804 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_elemento_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id FOREIGN KEY (elemento_proyecto_elemento_id) REFERENCES public.elemento(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id;
       public          ciecyt_admin    false    206    205    2314            M	           2606    16809 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id FOREIGN KEY (elemento_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id;
       public          ciecyt_admin    false    206    2282    189            I	           2606    16789 L   entidad_financiadora fk_entidad_financiadora_entidad_financiadora_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id FOREIGN KEY (entidad_financiadora_entidad_id) REFERENCES public.entidad(id);
 v   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id;
       public          ciecyt_admin    false    204    2310    203            7	           2606    16443 8   jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);
 b   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT fk_evt_pers_audit_evt_data;
       public          ciecyt_admin    false    188    187    2277            T	           2606    16844 !   fases fk_fases_fases_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fk_fases_fases_modalidad_id FOREIGN KEY (fases_modalidad_id) REFERENCES public.modalidad(id);
 K   ALTER TABLE ONLY public.fases DROP CONSTRAINT fk_fases_fases_modalidad_id;
       public          ciecyt_admin    false    212    2290    193            b	           2606    16914 4   ficha_tecnica fk_ficha_tecnica_ficha_tecnica_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id FOREIGN KEY (ficha_tecnica_user_id) REFERENCES public.jhi_user(id);
 ^   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id;
       public          ciecyt_admin    false    221    184    2266            D	           2606    16764 F   impactos_esperados fk_impactos_esperados_impactos_esperado_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id FOREIGN KEY (impactos_esperado_proyecto_id) REFERENCES public.proyecto(id);
 p   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id;
       public          ciecyt_admin    false    2282    199    189            [	           2606    16879 J   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id FOREIGN KEY (integrante_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id;
       public          ciecyt_admin    false    2282    189    216            \	           2606    16884 Q   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id FOREIGN KEY (integrante_proyecto_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 {   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id;
       public          ciecyt_admin    false    211    216    2326            Z	           2606    16874 F   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id FOREIGN KEY (integrante_proyecto_user_id) REFERENCES public.jhi_user(id);
 p   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id;
       public          ciecyt_admin    false    184    216    2266            =	           2606    16729 J   linea_investigacion fk_linea_investigacion_linea_investigacion_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_investigacion_facultad_id FOREIGN KEY (linea_investigacion_facultad_id) REFERENCES public.facultad(id);
 t   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_investigacion_facultad_id;
       public          ciecyt_admin    false    192    190    2288            <	           2606    16724 9   linea_investigacion fk_linea_investigacion_linea_padre_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_padre_id FOREIGN KEY (linea_padre_id) REFERENCES public.linea_investigacion(id);
 c   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_padre_id;
       public          ciecyt_admin    false    190    2284    190            d	           2606    16924    menu fk_menu_menu_padre_id    FK CONSTRAINT     ~   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fk_menu_menu_padre_id FOREIGN KEY (menu_padre_id) REFERENCES public.menu(id);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT fk_menu_menu_padre_id;
       public          ciecyt_admin    false    224    224    2352            >	           2606    16734 +   modalidad fk_modalidad_modalidad_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT fk_modalidad_modalidad_acuerdo_id FOREIGN KEY (modalidad_acuerdo_id) REFERENCES public.acuerdo(id);
 U   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT fk_modalidad_modalidad_acuerdo_id;
       public          ciecyt_admin    false    194    193    2292            O	           2606    16819 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public          ciecyt_admin    false    209    193    2290            P	           2606    16824 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public          ciecyt_admin    false    209    211    2326            N	           2606    16814 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public          ciecyt_admin    false    209    208    2320            H	           2606    16784 C   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id FOREIGN KEY (presupuesto_valor_entidad_id) REFERENCES public.entidad(id);
 m   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id;
       public          ciecyt_admin    false    202    2310    203            G	           2606    16779 D   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id FOREIGN KEY (presupuesto_valor_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id;
       public          ciecyt_admin    false    2282    189    202            F	           2606    16774 A   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_rubro_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id FOREIGN KEY (presupuesto_valor_rubro_id) REFERENCES public.rubro(id);
 k   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id;
       public          ciecyt_admin    false    2306    202    201            B	           2606    16754 D   producto_proyecto fk_producto_proyecto_producto_proyecto_producto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id FOREIGN KEY (producto_proyecto_producto_id) REFERENCES public.producto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id;
       public          ciecyt_admin    false    198    197    2298            C	           2606    16759 D   producto_proyecto fk_producto_proyecto_producto_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id FOREIGN KEY (producto_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id;
       public          ciecyt_admin    false    189    2282    198            ;	           2606    16719     proyecto fk_proyecto_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);
 J   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_facultad_id;
       public          ciecyt_admin    false    2288    192    189            U	           2606    16849 5   proyecto_fase fk_proyecto_fase_proyecto_fase_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id FOREIGN KEY (proyecto_fase_fases_id) REFERENCES public.fases(id);
 _   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id;
       public          ciecyt_admin    false    212    2328    213            V	           2606    16854 8   proyecto_fase fk_proyecto_fase_proyecto_fase_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id FOREIGN KEY (proyecto_fase_proyecto_id) REFERENCES public.proyecto(id);
 b   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id;
       public          ciecyt_admin    false    189    213    2282            9	           2606    16709 0   proyecto fk_proyecto_proyecto_grupo_semillero_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);
 Z   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id;
       public          ciecyt_admin    false    191    189    2286            8	           2606    16704 4   proyecto fk_proyecto_proyecto_linea_investigacion_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);
 ^   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id;
       public          ciecyt_admin    false    2284    190    189            :	           2606    16714 *   proyecto fk_proyecto_proyecto_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_modalidad_id;
       public          ciecyt_admin    false    2290    193    189            Q	           2606    16829 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id FOREIGN KEY (proyecto_respuestas_pregunta_id) REFERENCES public.pregunta(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id;
       public          ciecyt_admin    false    209    210    2322            R	           2606    16834 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id FOREIGN KEY (proyecto_respuestas_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id;
       public          ciecyt_admin    false    210    189    2282            A	           2606    16749 M   resultados_esperados fk_resultados_esperados_resultados_esperados_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id FOREIGN KEY (resultados_esperados_proyecto_id) REFERENCES public.proyecto(id);
 w   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id;
       public          ciecyt_admin    false    196    189    2282            _	           2606    16899 I   retroalimentacion fk_retroalimentacion_retroalimentacion_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id FOREIGN KEY (retroalimentacion_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 s   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id;
       public          ciecyt_admin    false    213    219    2330            `	           2606    16904 @   retroalimentacion fk_retroalimentacion_retroalimentacion_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id FOREIGN KEY (retroalimentacion_user_id) REFERENCES public.jhi_user(id);
 j   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id;
       public          ciecyt_admin    false    184    219    2266            e	           2606    16929 %   rol_menu fk_rol_menu_rol_menu_menu_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT fk_rol_menu_rol_menu_menu_id FOREIGN KEY (rol_menu_menu_id) REFERENCES public.menu(id);
 O   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT fk_rol_menu_rol_menu_menu_id;
       public          ciecyt_admin    false    224    225    2352            S	           2606    16839 ?   roles_modalidad fk_roles_modalidad_roles_modalidad_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id FOREIGN KEY (roles_modalidad_modalidad_id) REFERENCES public.modalidad(id);
 i   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id;
       public          ciecyt_admin    false    193    2290    211            ]	           2606    16889 7   solicitud fk_solicitud_solicitud_integrante_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id FOREIGN KEY (solicitud_integrante_proyecto_id) REFERENCES public.integrante_proyecto(id);
 a   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id;
       public          ciecyt_admin    false    216    217    2336            6	           2606    16426    jhi_user_authority fk_user_id    FK CONSTRAINT        ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);
 G   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_user_id;
       public          ciecyt_admin    false    2266    186    184            �	   8   x�3450�(*MMJTpL.M-J�Wpt	�4�4442"N##]c]#�=... JNL      �	   �  x�}SKO�@>O~��W~%!�@--)��fw��Y�Z�p0��CD+�0'[���vGN�	�,�e]eU�����i0�%
=V��������!�Vk�]C��nͨ�&3y���c} ���z����7o�˲ma'*�(X��(���E��xMG�&����)�<�<u7�?�P���f�6+�Y?�c�qr���O�ZJs�v�)���Rz�y&mE�>�~2x��?�!�ѳ�Pm���9�Ɖ��G�u�"�rWD��&�M\$�}^�n�5b6�w墊xE��Y�M	[ӳ�$'1{%Xڿ���e��D��K���8eC�,M��ś���e]�]`�y�=nG��Q���5��/�%㥤1��^����>�L$vv�s�V���𜁣�T<<gv�~ͽrs�]�V�@��R���H����ۜ����C�^�i.�v�.��G�VZ*��9�s�� �U	��g�~H�xU�k��T�8�a½�_��Q��=9݉��|J����_��.�      �	     x�}��n�@E��W�\XK�Rq��A��lh���f�Ȯ����E�z�� qH�{�(�מ|05��t1_$�4����IQ]d����m�cOgؚ�Md�<����Y
Ob.�q�a���`���?O��,���XƟg7���&�,��8�Ȯ:0�g{d���v�>7>��Vܝ�h�Jh�?x��}D}I��%8=�2��#yY�Qh�Tҳ,��f���#i����AL����ĩJ�iD�r��։���n�r���-�T�����kF*�˴\��<)�F�D=�z��s�O�
�N�ݠ� �dCp66.+���W�L��;1��hxt�n���o�.`>�����0������r�0[uz؋iZ���G��PqP���S_�j4i�S!��?���+8	��v�$)K���)4	f��5��_�h��M��ν�����:v;Gc��*_-�*?7���^5kGc���k:���]��u���k=l�o���_f��ȇ/�      
     x�=��N�0�ϛ�� ��s�AQ[����^g�Zr��vR�ӳ΁�=���\[2#��,������Xgf�uu��In���J����K����t�H��ە���%M�My1Es���v��Voͤ�O�%{Rm��^�Noہc"�/��qGnjwVB�qs��"9-��^^ˢT�c�8���xS�#��g�����LS�#,��NhpEB�j�>6Z�c	Q��";�V�mH�ݓd{�e�H��b��J�(N���UU��~�      �	   '   x�3430�(*MMJTp�L���4455�\1z\\\ �C�      �	   s   x�M��	�0DϻU�%;��&� QE�W���00�+hZ�qV~=�sVB h�L�p)Z���вI������#X0�<`��ݠ�M��4���@2�\����h�]��7x�(�      �	   V  x�uQKO�@>/�b� ���c�D$F4^�L�����4�[�S5����0߻o6�J>!�jŖ�4�ޠ�������92XUAЖp%�����}A�yYtf�A� �W#��C��y�|��(?�aͱ�9l�l�_��y������c�lj���0%O�-L�,'���-ņ��d�J�rv����X�%��$0�����5�����6a��Wg��F;��=��kd+u����ܱm(ϵ���AwHb�����=�!�M&P�_7$fƺ�6��i*��gݗf�$�MƂ*�����d�>��/��{fWj�`(Zt��D����q�%���I��� ���      �	   �   x�u�=o�@�g��T@ү�6�:u(k�瀕���(���CO���y��6E[�uU7U���vf�:���)��F�����U�fd��"t�w����$ m�/���؟W�Ӷ����_zWEoN=ض
�%�9C/Lg� i����9�x��UFu!ѯ��Q]D跔x4�A��q��o��H#�C��37���}��:�}�zT��+�EL��,�_$��      �	      x��][���~.�
=�Q���Cҋ}kal��؇ ^�Nf���� �_��=�Q��X]�rK�G}���C��9d���u���1퇰�����n����!}~u����÷��s���>n���C�5ݹͿ�n�8{��[o8�N��nPK5���������������e-�`�1�J3+��Qs4�a��cz��mz�X~��ݥ����yzH��x���==���o?M]T~��.Ɵ��;�������k���i�ឮ�~w��w���7e��O���7����%��_����O~�f���_�<�nDyA�I#A����L/N凜�-�����"]}�aH�&�?���k��q�K���ަ��IGcQm 8&���	f�
<��CF'�01�<��\�=�Ѥ~(��<|�pv'm̊���R�j��\x�}t�	�ך�V+6��9.�[+TH 4�D��+6�6���U���=ni�,��k��Oi[4����c�QB4!Z���dc֚��\�!�}|�4��!��a|�������)���mh�P���E���@6kk�����Z�E�PF��x4�l�re���pXD��>�}yќ N�h�I�z9�R50"i|
�mr
������<x�:����pfL&�Q6J�����2�f�눈�X��Oi��\�H@�M@s�1���݇�k��Q\J�a*��^Z%BR�	���VK�E2�?!axk>�N�tg��'6���h��A�c�J��1�9H�9" �6K�p�灖�\�n�����^^^��	$����Ű�~'����#!J��[��l�fD@�$N<�NAd�W�TV6����#F��y��X.-�3Ns��;���T2)�tE;'ѻ�B4Ȭ�$0�52��b�A��e��L#Υ,�^�o1��)|zlbV�uǰ��
%Yh4b��L&�!���;͊n�a�%$tg�����6l�Z<�l�Ԥ����&�R��'���Lj{tR��|t=?|j	P�T��?��������&`�Q$��V�:�����NXАPAp�Lx:����<Q��߈�����Z.kj�7��h�Dfh3���6o�8�~�<�͚y�=̌�0��<<���%���*`ɉ�;R��QiPJ�>&4���y��&�~˗[�'���S���ĵ�9�r +�[(�i)�u6��,2z���;R>�0�e񙍏9*^Y����}r7�������ڸ��߽٧���>���7�\;׏��j��|oJ��Iy&x�H9!��Ɛn,G�q������NޕMr�]J���x}G�r�Z��� �����r���O��Oo���鞬����"�Ι��n���g��)�?�d�o��~'qc��J@�S:}��}��?� �'��QX0�i"�	
��P�!f���B�FQ�3����'�A�x���9m$�3@�He�ߪ�C"�T2"ؠ�v?f��nztC�)��p�����w���rAa�@JqL�O��)���s&q�H��|ޣ�	�щlN�S�X���([�n{�/����@��2���]�	�ֹ�{�9ij��ݕ!����Q��3=Fc�PZpt����sb��������H��c�C���j��ER��-�h$��T�5'����N\{�<��,x$�dI66����f�֭��h���+.�^_�b8z�8�^�|��ۼ=��(eI�:cS A��x��V��..O޳<����+Z���k�b^g��G�|��r�J��������Z��#�R�V�Y��2�Q	/��
�P*��R,����^$�^��N���>:R�gį�Q|���ؖ�@��� �A�he�D;���7�2 H�D1�J3�d)Q&a��I��3 }Т���e�%��lJdL�A^S��N�X�$�!)�Ϥ;?
�X$�AÜ �r����j]H�u)1�j5��F�_1�:����c�n��sS5%�����E�N�� ���˘i�Z�����<yk6�U�n?�A]��hnĀ���`Z��+�� ���U���"�Ȓ���<3)��V8�b�At�=ͷ9���-�m6�J�{0�eg<:�y@II��m���y��gnVX��O�d
�'~��Q�YDך#Q�V�"(�A��<��P�1L�[ڼ�}�s�
��^ۗ�<!>=ڇ�~�S�(�F8�����O�X�^�@3ae���Ĕ��FF��Cm�Ď��;O�����wzw@�h��{���8���s6�]%D$�6��H�~w{s�^��p3=a&ӎM�v9ڍd
9Q�� hE�j��e�#�A�3xctJ\�a��!�c����>��O׈:u>ق�]�JEZ�!)$킁_OWz�֧���2��UmЄ(�u*OkU��%nh�������d��E�Y�Xak�=�TC��c�D���1�A�&��B�"��M�:�M�<��>��A�LI�� ��$��h��D�Hy��gbT� �"�;���ɰܔ����l��d��ha#�҈�18�v-R���^N�]��C��#a5WdR�⊤^"� *-Y0�kG�]�< c�"�Ƭ���Ϥ{U� �ܢݨ-=�D%kDp�L%�#�&`�y2	��N��P�:0�@�qe~M&`�!���g�����r(u2�q��v��e�~ܛ|�9��F�RI*�l��D樼/�Q�Ө��}��=�=>]�Ҟ�d�(��s��9 ��.j��j�u+6=��>��Y�����5O�H������훨���� ��}�֗�ݍ
A	3�J��QwցG&+�ޥT!E���'�g�O��x��2��R��W)�֫�d�G�gL��n�z�'�)�N���$�A����B�i��
�.Gj�bj���*�餛Fq��޻��%f�o�-i��Ҋ��F�@d�����j+H����B��|4��k(�fTq5��,>��c?kp(��JAt�" s�7CL����i��E���	�|�p�f��*.H�ơ	�ZA�V�R8Nz�n��Lzl6y����a��`V�2')�T��-fH��L��,�R@6F�s�sJ¾^!����Ԡ*��b�
��܁Ң��}b�m2��A��7�Up}��.�+ŶzP����'�	BI��1xǓ�AҞ�(�a�"�k����>$C���ψ�(���0�,��*y�1GBC�K&
���Lj�uE�")�ci|g�֋d�	U�52=�.��YT� !�%��)M
��'�Rcw�
�鹪��t��"��+�>�̿B���~��R�$O���J�	�'�[�C$0���ń�.�[�l^9`]���7�E�w"�\z⫤���N��Ҏ�[��2���s~Nе�����@eE"�!�!���t}�#�U+y�5X�w6uW���� ���������-���!m+��	�r�},��������$�bх4�2Jձ�\�f�Z;'�P�_�M�u$=�D��:ĲU����e�����Tb�o^^<����y)���&�4��y���B���RB5�-Wj�:��������E,	�$X�i� ��
��!o����ٯ��IG�b��ˇ|ַW�0	�6DL]��YH��΢�ȒDbH�gУq�6%/f*u����N�T��@�m�u�?�F$�`������ZC1O�������9�������=���^E�QD�ʌ>���h!��}̙`8�\�J���Y�6��5ۗ.	�KڃW���:?ew�i9����NJ��ӭ��T�b%��V��SiU�Z�y̤��.�|<�=/�_C�u�ɇ�\1�z�wp�����1(�y��e6(�����8qct�84�L^))���v�}v�ֵ�Z3�8��b+��M�A�YY����j��E�Q"0�8$�r�G��i���w�5Zѽ(S��&��$����.�z�,1� $����v4M�u.��@�������i�*��(�8�+���`�C��l%;8㓵��(i��ڎ��6�q��R����\_�-�Ԛ������E����1+	YG�#���k�#�4��U8�@�Q���N=����@F�*	C�$�V��,�k��f�Pڮ](~�' �  �:o����4ϳW�iB��N\$�t��퍷FhN�2aB`���y#$��6�V���<;�r�o�G\u5�@���� 슮.�vuu@��y1��U��9����$]H���B�F]
*^l����,IcJ�V�o�J��3�	=��Ѐ�){&��W�,J�$/_���ō�3�R�+���:�ⶎ$4Hf��{�w�/FcJ��J6h������T2��7V)�:C��h��y�ʋ5�����M'�f�j#9Q��Yw����L�nvA�D�B��SN3IZo	���smh=K�$	>��N�c<}嬋����r�G%��@2?A��F����z-q4�pYkI�^�c�Z�csV~��(#�&����\��t�7�<�h�C�f�����M7W<ӂ�������I6?'�n��J%���VΘ��DرI�n����Ë��S;��J�)tiYw\+::_~��~N^|}��n0����վl��hls�rŒM {(%/S�b�gD������\���I49}����O�+�傥�\�dKH�љ�,g�D�L��Rnb��x\��D�~{�������l��j�\Y� 흶�T�-�r�jR��ײ���`9�Kw�,Gk������] �rZ.|�%D�=D�m��J�!���@8.�M��d���8����<ٲ(�z��Ӽ�㩜
_����2��cQ��y{�'���sԣ���o���T�1�2���8���u��06u��\cJ{���̉�)�^�F�"�D�E�H9���u#|�ys��n�ˏ'�z�A=��i�1�f'؍��[�c��$�톑�F?;��"їcb��lh�{X��/��k���;�6�
��|�^_;�G9Wu����=�����/���b��ۉ��r��PrC�]����}�Z��//?��}�OtQ��nx�T�� �Q�ʈR�d��j�Cϣ��h�����HC���x}�b��
�z�~i����J���̶i��lP�U�	��D钂�$U�S��N�粐�Vj�l��%��l���,�-��=4�I�vN�%�QL�r�O}�3�c#I�2	K�Ϟbo���$=k��0)닦>����=O���=ǽ��S�T������T9��>
���N3�UQeG+(��Y҄�0W5;k�4���]u��o(�_3����w�bg'�lX�Ι�P����1J/�B	��y^J.6].ق_^��*{:���[x��F3�$UN���������$h���ȹP���C�C�	�Ϭ���ܖ�Q��i^e�{�ʒ���6e	6��I��a]��x�V�*gQ25\� �'���=�d���,#2.�����$�>�RߑXh�1���׮#]����0��k��+	>[� :%3�r�sZ�޴��������ѳӝ���e���4�'WjVI`A
$�J#)^�9�*=�dJ3�դ�|�-�r�PST-=k���u�Q���gw��%u��D�jI&ϙ�<�XL���<
���ƹ����ã�����?��ՄR���jT�ɿT�b7V +z���Qx-����/�^�:������<+39���%'4�X\4�=)�e&g�;z������K+u�	��?��yŌ��gG+��C����
ƹ��E#�tL�\�z�3.	f���O+5Fv��	q���f�Js��S����O��ݥ2=�����*�|��y���=RTDO�*?�@_p�nV�hA�nGb"�3��V=��9"��r�>@�[���o����C>>�      �	   F   x�3�,�4202�5��50U02�2��22�346��	�q�50�pV�0�4�34�г��3������� ��      �	   {  x��ZMsǑ=E��1L�)K-ӶvI-C��t��6��k\�=����������{�U�1 �]��a��`��AwWe�˗/�������ş���|뢱�kg���������s�t�y���l۹���狋�O.����ؿ���m4��)�Ƹ��M��Қ��l����ڸ�IK�ۊ�ָg��P��Wۢ:ܴ��ñ2�۹��f]�Ɩ����ˋŷ�w]_{���%6�'�r&�`��o��3t?o�6X��.t�����pxź�̵����3}�O�]�F<p���M�M��U�:���)����
u�T\��pf]ض�m{�b�����#�vW[�e�j�ѯv�����FC#-�v��2��W�����zl���v��!u^배�M��v�~���U��_��*r�|�S�����Җ�m�x��";��p��&`A|Lw�G��;|�U���j� ���a� 46�t�p��/�_�q|0�{�{`"�u���q!��/^��%�.�*/�ǯnӷ��e^X�s��_���F6y:�j,B�R�?PͶv� ��]��k�������#~Oe���7�Q�m����\����� �Ζv9�*�&[� x
n��)[إ ��6���ڔ�L#��z0wI��1~�vS�}�,��UX�1���t�3f0��1�S�+��i�"�y#V���o�%03�-+�R�b�5������� $�_�Ip��ߵ����?zlz=<����64U�	?�3�8ǽ>���I�6߁Ux����#/a9!>�/�0��M�;��"Y��)������ڧ� D�'�p�45��j��[����'��J�c�"�'�=���Rx(�j�� ���M��r��j�=��5���4�m�ЧV[��������9�ve�ȥ���P�7G*���$OT�6'秣��Z
���=��=�C�}�8��2\�&���u�#�~��x�(G���B7��mm���hx`v���H�U�!{E�4�����j�o@R��ׇ�����3�]��g���3��61�1�~O9�����Η�������K�rl�끛�ɷ��Q
���Z~'a�'�����������#�+^�զ���`70��O�*��D����QS�"�������= 	��g��^R��eＦ��$d�/a��ؾ#S/y#�VIw�CŚ@6�>�}�m�Y���h�VWU�e{������vu��D������U������q�?���~�ΐg#������>ء��jg_1z�o�#Ji��7�^U$~��#ϋH�c�8%y��V+<��o�$c)r�uhRGD����˦�ٮ�޶���vɚ(8���?�R��׾Y!���?,^*����nZ>���YSb��ƃV)s�T�W��Ȅ��@��Δ�g<�#�L3πv�T��B�{��@���iIM�j�ZU&�I�[|f�$�P����CԽ�����h�~��ĞYI&�nl��e�\���������[��-�R��=L�lYF���K�����ߩ���0i �6��*J�L#��:� ����u�|"֗V\�����{��-��E���4���>:R��h�U|ͧ��mʭ�j0��*/��h��v��7�Kz�^��s霒�Fc`���%(���\��A���ꦏB՚_ZԚ�Е2�#LOA�!"W���oN�*u<����Y�,8��5�K��2x�p!Hi�h9{���i�&2�D�yi��<O�f�T)wP��y�/�3g���� �DPGѯ#�I':ɝ�`(2����*CS�����D6�f>�V
j�y��7V��*�`��~E�ی��w_�@xpq�ީ�sj�,��0�����d�ؽXuq9����J���(3yB*g����	�Oe��<A�>�3����Mۊ���z�nXW����r�ے�Ap���:+Uz0:Zv��{��;�"x�jZ�5��K+5�>+=B�N��BrW���WsxK:���I�����m����A�]�<���~s�&w���Z�|�!1���9�Ԝ�J��k��V�F�T�����GXT�I�q��R2��{����!l%A�,�S&[d���u&`x�x�"���p�e��+����L���'�Ka�*vNu�ǋ�t����8]��$�����d�?f������ŗm'd8����mQ���>��L���p���L�f&��y�04x	$
�8��X�}���Y'�k��EqB��>Y��=j�Y~�0Aͥ&(W�[���j���89�v�Y��S��Չ��dG�I�0�|v�R��2�8o��͐2}���I̘'l��͝^�Z�ifke�Ȭ��=�(���Q�>]���?�w�gʡk�H�Sy\�k�Ut�����H�Ȗ>Y|�j���0]�k�Ra;+
�R�D W���}`R}鰌�Djע$r=�;�q!b������D��S_�Z�Jj��_lZ�}{����=l\�������%=v��1Js9��Z��`��Ԋ��� JM��Nƥ�%�[U��RO��af�5�� ��!@{�L���4a^S�<���#L�,ۤ�n�ML�3�a A#����,�W�a�#2?���/���NY�zF��tU�zfZ�H��,����Jn9��w�V����b��uݿI���1��Y!�:cD���:��JB~h�G��yX��>���e֭|��[;&��Җ�W�HGIV�8-� ���,&;�GÏd��i�qk�|d�R9���$�p����~Q�$��w�r]����sh���6f�.��}W�t������^w�Ru��#rQ����g�����e�.��l����וN��8���ᯣ�#�ݽu��Òm��I���\�$8�]"�bN2�,'�����_?����5FcӜ{�;,��9���K��B�u�c?9l����������pcV���*�|ɶG��hY��<�h��כ������ŵCR�>ڡ����@*MQ@�� ����DX��_�N���{�o��� ä 
-ԍiR��W����i�� �B ˇ��m6*�i���g�0�v��\#L�#��Ix��aһ�B���T� eG��5�2"�(u�]����m(�.���}*�J�.�"6uLqtiK��]���q-��3qov|�'������Yg�`��+aNlt�h�����C���)+X`u|��1��aMq:� ��$y%#o��X��H���{P!�]��/$�3�YԜI�X�GS;�U֙�F�}\V��ے�����1���HmIQ�eV���q#���_��~�-�c�j��=Z�qɴI����~�a��Avӏ������ۓ!�8u�9���OG��p%o��H�U��֔�9�LHM�p����Q�Ue4S�?I�	^KQF���om������M/��g�C���T�W�!�����f�5�3u�p�����`QM����$X� �^��ʺ�^��QN�"l��1���E����T��35q���+�iޒ���ՎaC���2�PK2���:ɿ}*�I!7P� ��}}�����Y���ڽ��堞N[P���1�����S���uԬ4PW:(���t���s6�r"pB?���w����]'챶zh��*7��#U��$����hw*�����<�N��4����O�bcc�����w��e���	S��d�{A���A�$������^=3��#�Cd�x��K��@x�%���噥��%s�cM�#�����ܓ��%ƀpݣ�@�U�*��>�/>���o\��V���kɣ� �Z�Y����y�x����2�.:w�o/�|@/� ���˳��/0�NQ��5�ne�����J�x�c%z��WB�M�����J������/C������{���mh���;^�V��jއ���{��z��<��������Z      �	      x��}K��F���+�X��k����PزfZ7l__�gf�MH�)�H	T��7^j���f�]l��ċUv��w:,	�V� ����G&gW����+_�m�b�m�B�v��n�Ie_W�_%��&���+։)����l��|vEV��ܟ&��6&$Kky���c�_�ySf�7�[��Ej����t&��O�"��
[�pc���I�&��^f��ȬBZ��2�Y�*ܖl�����Φn��x�4yQ�C]޸<Px,���W�������Y�bYW�Ƀ�����ms�V�/\ڧ�$�+HFq��KH7�X�L^|�����M�'�D�]_LCr몍|,�
,���J�Y%acm��U|K��;�Ys�$�W0��Ȱ�?T~���e����n��h�y>%�b��A�3K�:�}�Pp�2�'g������z:;y��G3��|�Q�����H��"5�ԑ"u9��Q�F�:R�·"u1��(RG���P��F�E�H�����(R�H)RW=��^��'a�^�u��������n�RS&�I
�*��d�O n��L��`~�����&uw�	X���o�am[�����7^���a��ⓟ�R�ȹvS�>�}��kA>�:��,�2Lb�'������N>�����lԥQ���.Ũ�j��5F%�@'PC���|�d6jB+_~:��'xI.\��
���Gs/�H'�gC���Q����@�5�Q����P��y���j(Pco���z(P������y��r��Y��l���$�;:��^��.�_/�׋������������Wm�Df�W�ys}~x}>��8�~>�~vx�lx����bx����������������W��zǌ��y��Ąle�n�ͥ���ïg�DȺE��=��>��G����z__���|}=��I�ј�@n��L���Ib����?7����4����K��P�M
��d�9����o=Pr���0�H��� ���/�}�+9����,� �������2��I2W���9h�7䠂�SP�<X`���+H,�̾��W�r7u�,��[�̟&��1�M��&`�W�/[���|��z,&6Q�M���F��.���*��Bc*�:m3��Ӯ��w���~�0��p�p�3>�g!%[����rB�ݚ2���/�Kab����д��\��@�� k���d�^��:�)�Q��T��V�@�TW!������VF��qMn���֬W�Yԟ���6B�|5dcR��C�5���
n�?wc23��k_��ʍì���ʖ��\�,�᜾,���������l���Q�	W��A}8�V)3{�~��u���Ef�� ��|e&�Dt�5婠6�5��5[�-�_��!�c-��&&b#�.��ӈ�j����q���M�����|�,D�ǒߨ�G*�ET��y�zT��5�lԙA�1�nn:��@Vw?Wu�ŕ5�4P�{*8ԭ�����V����Q��?����T����	�4���5]�X�P�EkOi�Ȩ6&( NϞt+Uܟ��6���Q��Ԧ˨M_1^l�[z�3b,| xC4Ev2�z��ۡ@BDoB���%��̙�� w�y�E��M�"�e���3r�k��w������D�%�k#�ʦ���~YJ�Uz��,���d�1�9�N�7)����y{�YScu>k������IXe�U��<aW�O�-x��-|��|u���lzy�D���I��b�\��:�s]�r*��O�QI��Zp�Z2��Q[r?|z��h�M��h4����K�S¼h����jX/(%��y�F-I�|���S7�TZzj�ɟ�puEc<ჩ�UjY$�S������<��_����	cȏ�_Ů���m��X.�B��h�{6�����wݽ}�������@~�%��A�a��v~�{`�o�jd��c��=>���2��d�{��Ѷ���L�d�1L�v\�K
���^ƛ��L]M�U�~���ˤ0٪�߽-2�C#�j��<O�ݥ����~��]71q|u�ZD�-�h�x���=��7EwM��z��ZH�BM��鸣�(�Ȧ@�������d���sX�d������+�딐5QgO�
�*��%e�G�,�=Px�|)X#(����s��	��)�O��G{Zuɜ���ڙ!���b�%�Q�g�t
���9̡7e��)�<���~�����H0�t��\��9^4ôo�� ���'H��#Y}f��D��tȀEWMym>�z��k��+��
��2�b:�zG:�}�u�Ŵ׎!4��#2��&Jؘkx�rӋ�kI8����ˮ5KH2��\u�w��C�1������$����K��=�)C&�[�,��F���( ��u�0�랝�Y�A��}�{v�U�u�£�]����l.�j��$Y5����J����ܢ�{�ª�^��,��烕]ĕ}� ������hU�C��Wӳe.#e��O�^M/@Ӗ�󾰽G��������:.s��t��^��Փ�����χݯnNm���~��MOL ��e�!�;��f ��뇍WgC$)�o3����0x3� �YC�.B[돊Pf�t�Yu�mL����O
�fox�u�4��Hs>'���7Q�j�����A�� �^!��W�kT�#:S��Ah��j�]����Yu��� �}��z�ʧ�6�%���7������1�I�����S�Nr�.ػ���
��.\��ޓѭH���_ ��M,T�J .�	#��l�*�J�fud�U�$Xvz2�ON��<��G�,��е��,�&
1���s~K1Y爟N?�O��㶆��ǚO��A��pv�C����~��T�O��ebϿv��5��Dy�r$������8��U�1z���#q<=dTģ��QE���[Ԧ��Q��>%���>��i<(nT��O�l�i���;uS*���$��}:P�K�r�X�s��h�Ɵ������k�Fu~�\�	b���h��9go��$
�		'�$��B��2�̅�
j�?H�[�%+���崢f��N"��2�jq%������q�fB-������w�/�Z� �0+�J��~�*(J��	��n��x�lk�8�Y�n?!���F���	� Mq�/�/R�ĭ�KK���\��\@��7As���DN��M~����,P�Co��B���L\Ú�����%?i��EHV��VI�$�2�r0K���oY�˝�<�,�3���j�V{/#0,��G��ң����ڮ��5�r��|v5���艎�D�٥ۗ�><�Oe��hu�Guz�sW���:M����a��c���/����L���Q�{@��׌@'��h�a@f��ɫ��|�@�T;�m��o�v��ws���,Q��7�F%>V�/���x��igQ�EI��=:.q�YK�5���(��VӉ����}���: UA�)k��+��Q�?�V���w�]���υ�{��s/�LB��7Pi�X����ƱhT�c��"*+�wo߇���UH��%���%۲u9���t:}�n�Qp��OvS^fm��=�dА%��ǳyG!��[2��c��U�m�ٰP���\���}�>nȫ��
mu���Z�����ґI��(��7`�"��:B�'�أ}Sy*u���_`v|#��r�S�	�B=���u��wf&���$�H�O��O9mPT�;�"o<;y�c��6����	�{F�c�21��Z�L+�LL�v.���]5�B�<S`�E���uRa�\,� k�p�r(�L9���r��n���-��������ӗ�˂�O���� �'4�{v-�Dn�,xP����=x���Z�����r�Gߐ�k�8MRE�B�����o����2��'d���cZ~I��T������W�d�������j�O�i3����L�-��-.���եPK�Bݒ�!���jLn�P�-&�Ub�.��uˋ(1ϥ/A���Y�F]Lh3�Q14�������xݦY{R�Rt�|�$�Pw�R�mxBGC�5��P��f�o�b�/cx �qc��5    �;�Š���R����ř�}�\s�j�cn[�k.[U�[yT��";[��Q�0AO���4(pv.�u�|�D8���*H����]�̥pκ%�-Mn��H�3�~R3�_��_aM�P�%���0Rq�����X�w ��/�eq����"��2�B�\MƤ�.�[~�<�%,�9��\��8�`_���/ޛ�QeĖp���,+�t�ÿ�:N�79'��:�Z3��Z��Znl&x5���m����ŭ��9��	��~n��D��(�i���]{�kK�N����\�!�L�4;H���,�w�y��4^��b�wIja
�i1\l�^B� ��]�r�5O��i
�]э&�z�}$j����S�� b��|C�_��9�8�$�r�h����]���zv&L�ɒU���"ߋ9��X�&�*�.��{�����9M�3�[pC�䫎0"-m�&����;R����&��*f�<���ʽ�wX�D���Uw����i�x=6����1��4�~VKT��%��N�Ȳ*����*��4i��R�N�sRɏ�����Ŕ~�b�J`#��Āˉ#^iߤJ�=��asԆ��/ֱx,PT��6(!�榮]���F���F���OeiV¤�8��B(�R܀ic��>�o�Y�}�pV�t&q� ���AV|2�Q�� �(Ms����6Ĥ7���B�?�!��쀠��b�6/��ϴ>���)�F�t<������ؒ�*� ��M�_5gUۖ;Q�֤�zi)�tM�7�}7���F�{}�~�.��YQves��k3	򹴜FF���D�~f�c#�oۭh[ǯgWİ���}��s��$Bf(M�*T�MQ�j��f`5���e8�1�o`۠%�d ����!*��(RI��^��b,)F{��h\�`�0���%�1Ď�� T�l4�=N��Cq��4����閭�<^Q�X���G�-�`�90Djz*g]����"�C
��bV��z��`�ε]I��c/(aeR�j-���d�W��Gֈ����O����؇5Ԃ�|����&�w%�~�5�X�Г�R� �9Z�r��H��@���ӂ��|/�&6�ב�e��z�RB�n��+��2�D3�h�yHFBOq'*vq�C��{AtXm��Oi�$���HD�eJsĲ1����E.6�Om3��	|b����&Ös�grF�}L]�R5����2��Z<�����w�WN��'{٧�3��)f�ϠÍ����M+���-i?r����!�3���fsl1AЖ�ˮf~ϨN�g��
b��}G;�O�����6`D��U{-�D_iB����/��ѻ_^;V�8b^O�4%��� n�<�3K��*���wVk���nc��wBR�f�K��馅����� '%��)�չ��� \�G�_I۠��˵N�ap�^��HO0�q���d�u����ˠ�L6�-x|S�� ēa�X�EN�)��I�K5{�A�̭1��$ �q����("E���jL4[�)�M/�N�Ŕ��a���1���̛P�k"5�Vc�m,bͧ�i�k�m��w�&Lڊ�w=�6���NU�ɩ[��V0*��p� F����	�d�.H�P�&�l�wo�̢����9�a�^��ng'��7�Ʒ�S�>�Q�d��p`��j%��_fR6]!�|Ko�� ��7��H��M�i��bҥ �,��J\4�t���Fpdo-r�Q�� .S��1�x��0T�[2x ���=]���{H=t���X��N>ri �٘�C�P#�m>�y�!5�b}�oie<"�O=���H��}���7�;�<�[���ݽM#D`�A���#��_F��{��%J�TV��א#�ʦ0�J��D5���>5�SFAk)i/Ƭ-i��XO���aFO��L��1��ZL�d#B/I�ЫQEnui����bm�لZ��L� ���^ݤ��ΰ]�>�'������V8A�I�Cl��̂D$�^��0ԫ ~��7]FQn����Z�tI>��.��9�Fo6����5%�ρl��}�m�]} �6Ҥ$����,d�I�@1	k�-@�2��#�%���j`�<����cmyE�,eCM%��`u�+'Dǌ?��� q���o�'kW�z䙦XѥD3/��SXK�H�X�R��Ϋ��^��ϴ~RaTM�4}�P��,��M�c|+F��6<�"���JN�@Ү���eo�R.�;�u��X'��z�)�=]n6��n)WcA��bb)�g�j���.66�Zܨ8��Y�����Ơ�C�^v#�BəRxD^�m'ul���{�^�O"*�m2$&��1ɡ��|!�)�0�E�)�gc#�6zHi��ŕ�[��&e=	�3|��ݕ�H�=�t�r\���g4���2����L��ho�^#6�ܸ���E�����#��9 �ôھ=ѷ�3����F)���5='0¤WL���=���y��]B���vt=8o��F��iZh"��f��i�l��ls���$y�*��W<<��֮�=1�%S&E�"h�9��U��b̶1�IK��$��	
�P�R�O�I�I��hKS�>�@�o͔�e�a��`�@\B���p/5��M�0��%�vM�e��7��*2�X�z����4UbS܍n��6-�^���V�c�6e��\��;ئ{C^�d��jv�t?��G4�eYcxH����D6���I"�JE��b����Lzm"�^�.�M/����]�a����
�2GS0�;�Bt�y���}�l���m�+v��?�"u{�ɤM�ՙ���x7�f�z�'�{����a�MSO���oO�!
���P
��A+j���|�H66�ӨF������x�`�
4QH�#$���Ͳ�sg'�)�W���pL0o�d��/;_�qg���rܮ\0v�+M��t7��o'��z��cz/'����*1�#1>�c@�s�����P-�"��$��N*�g�5�[q��3+���PD���r@��8Yt�jq����gzJq�p�����t��!
��9��w����:}����?�@4�����Y%1�O��g��3�@�&M��p�%-�=��^�K�xхx+�i�ԁo}��A�o�s6y=uzk�eT���ku�"�
-8$'�Q���DZ��|L�7e�zɪ��u����*F�q��g����b.sѿ|����@����Z{!%����=��F��y�� �o\�$Y��2[���S��i�׍�j("��<w�TX���Rp���jBgߊZV�J2�|g�@̂+�V�<�H��[��i�{�({z�h�'���WQ��PI:�������w�rd��K-���
}��<��%=,��UV�s�od���;-�%��w+zc��V˦M6; �LDd�y�&����L@���Ls�d�xwlQܘ��΢�|��d��(mp��N�8yQ�L3w-��V��> ,��2`������{ɨ�`�I�P��uIDC���c� I`S������R�����u8D��A/���5b�>��'9��"��̓G1 $�D�!���q���N�����}��*^oW�
�5��!���16鿵K]�͘�lo�A��gB��̮�.�`�d��e�`y#z�qo������8�-��W1Ł��Nk"@\<��9lUc�6�1g|&#���&�+�r�1`2�U����}��A]���cW��"�z��(R��'��j]D�w ���@����wy� �*a�S�4��U��k�c�D�Md{�̍q��M2��H ��E�U| �W�е�^Ԑ�H".1���`n)�+	��ĭ*�/>�
hoXU��z��Z��8�J`Ad-�x�.Ӭ�7��U[��� H����"�����/��q�n6х�S�xJ@���2~�N&*��z��V�k�Ȟ��l��ؿj�S��i X��U�H�BUֶ{��;���
6:I�S�I:��v)3��3�E��s>��H����F`�>�F��ڕ�d�T����o��[x0a���ߚ=����YˊZ�zH�
_|BԬ�9�TW�$��؜�^L8�=��';=���d ����/�߈~�iS�[ �  ���� -o:%c�6O`���qw�i���������l0���`��"�P�2�>"VQ�T����!�7'V�J�>�����ɗ�S]֒��e�"��N%�F�^��T�1��<o(dٱ���Si�d��18=EpH�����us�Mq_Z����Ox���R�t�|�j���dNHL���-��,!�\F�������}?��~G�E�]��ĩ���WR<�q*l��	X��N��˄��ī�td��۔]K���Jי~K�_Zf��JYn�4��u�@m���2Q��,N�Zf�������c�K�^�(mc�t��vV�Yp"�<?�������j �{���԰���?���f�(bl�)5��i��s���q��P(<ɩ	���g�=��� �e��}�}9rg��}_��C��賍g'����Z����<h�봼.�&���sZ�W�Z��}5'U�]�<�����m\ޜ�ml�5�o��I�3�J��k�	��J<���D��,���U��皻A7��Fǳ>`�����(?�g��d܈���K�Ƙ�n�����K/4\��&���[H�������<�DK�
�4D��1% n3y�!�����[�A���8 ���9�� lf��g�*	A��R}Jf�� �?F�R\FhS����v�xY��&�����JX��2��������R&�N�Zj�Q&�d�5�A��!�$����ގ������������l[�ɾf���1%�%c:��w�F�و��kjW�A�
�<{+�7��~���yW=M���$t�����"!&����e�������Q^�Za�F�b��{jI��"i���%�<��2@�8��>�O�����yz=����S�v��`�IE�[i6��k��w�f-�_�Ը��A:O�{(gT ���T�W���h�B����v2D.֥B �ȉ�� �AA�`��ߏ��5�M0Q����TK���k�/�A���87��V�?^@~.�G�lOY ����� �[�L�xЇ�A<�@�VF� ��;zc�_x��qW�h�*/A �şP��: �Z��(0Ы��2����wD��b������!���KW���d�ԁxgF����l��Kj_N�̸3�̶U�a�����0�R	Ƴ�}݆}��fş��N"�N��
NN�YG���hy����������5�R���cJ7���#�o͓x�o�rLX1��֪�Sdi�ظ�.t$���6������k�>�e$�'�X��tYq�&��]k�U[���d*�o�g�oL�0�66ctnL��џ�.I����>��w-ե�J�}���G�vH+K8���e��se\/U��վ���Z��8'M�Ѹ���
�������)]!�+-��������ZRT�U>�L���X��	R�U���v����6����S�;��~�6�����R��9r3(��z�9(� O/��%�!]
�$�m��Y��Z-�8%�$��Y��Wě�;t3,����"҅�߁#�C�6&/E\ U\�^�����K2�W)�[��wҁ��@�Z�l���|��Wq��/�\R�}3��ݲ�)�v��6>�B�Gj-� �nQ����sޟ&��&&�}RQ�����q�6�{6��j���
yV��|_P���$�wS~��o)�[G'��NNL>zy��G�7�Cr      �	     x�5�KN�@�מS�	�Ͳ�]E�e��L��jbG�ކp�^��������*�����/�G���Zr���+x��*6�	7��֘K���)&N�$�k�h��v�S�����xB	����h["2���rR�#�~�qO6	A��n�5��-S�fD�Dγ��ز0�;Xc���!�����۔J.�
d!_u�,�?��C��ò-��|гU���=@u���3�ҁ�E��m������>���0@I�J��-.O5�d�yB����}@H�����ׅs����6      �	   x   x�U�A!��yLE�ؿ���5���8�#E���>����K��Jx��&:��7\�խ�B-� �?1��$�{��BC���ٙ��)|�ܲV3W)�����z �����W)�y�-�      �	   '   x�3175�4�(*MMJTHIUHKL.�)IL����� �|u      �	   �   x�-��j�0E��W�Z:}w;��nJ�W��(��q� ;��׃g��s�� 6���;-�3�9N����<B�^FL�2��=������4�[������鈣��&�@ǁU,���%EӚ�ʿ�//�<�)�O8���J������Y�{�TNv>^8a&u|ߡ�/�n�;��u�cQ�P�m�^�f�eZw���Ji7O����qa-eX�-�
��Y�e)       
   �  x�=��v�@���S��
�4-�BCOsNW��3�-2���}z�4����|�^�����
��J���w�����R⌃Ă]b��M[�D��V�sX�	[rۆ���H�%�+p�t#�٨���4�n���ӈ��"�k�wK8ÏD�x�~����4��Xht�����K|Ӗ�����P~r)⺗L�g��?�#�l\������3�iR�l��z�юZ	RF|�X�):ƅ�ZL�fl�w��K7#��]�l�tp���I_C�������9K9�)J.����ȟ,>p�4~���W����%Lm`��*�v���D˻O��"�NL�H̅`j橳�<{<�Y�����ӑU��.��~�b
_Aw��5�{���ly�5�L����޴p6��ς�6��y,{ۋ����?n�3ڸ0}��^�A[c�&�_b��h���"��ȑ�mdQ������46vfv�V���l6����_      �	   8   x�3140�(*MMJTHIUH�/�M,��4�3�3�0�4202�50�54����� =/P      �	   5   x�3355�(*MMJTHIU(N����I-�W�L�2���Wp,��LN�b���� Q�)      �	   >   x�3�045���/J�U�,().�U0�4B@��[ Uq5��5�4F@�B4s��qqq ��%'      
   �   x�U�=n�0�g��@��w�:u!$% ��~
4���v)8|���۬��P�'��W��*?�C�r�����܎i�f�>x���I���DZȰ}Z5jpE$e�iAh�8��o��!�+�Np+tw\��<�<���/�R�j�5�|JK�qtIp������y�w�{뉾�A������w�����`1�b7��f���h��y��3$X�      �	   �  x�e��� ������ī�����H�(�[��؁�������3��{�?��?���a9�s򇖋J��5�˭�6�ՇIZqe,lQ7�"�bʖ���e';���$Z�n[*�U�-W��doZYV!����8�=��˄���Q�D$��Мt9�&��030eV�ȼ��8%�{3z �h����H�!5�&s�!*#SeK2rUnÄg�g�N�Un����j�^�ӈǲ��o!"==�'�b�#�w3h�2o*g������	f���L��X�dH��ћz졞t�^������.7-|go�,+�5f,8f�ڨs��5fl����8�(�_��U��5�89��0N�@DV����l�,��qw\Gvñp.*|�~Q*�8/�ۿ�B��!0�)�"��X߽�P�X^P5���Q6�q�d���2�-}��h�h{Q�hQ�4��h���H&�ǉ�+,z�����=Q�;�����t2����q�-($��./�����|>��W      �	   G   x���q�wt����
1C�]� ,�`�`(�����/��q�tu���]�CB]<�r^�A�.�\1z\\\ �:�      �	   �  x����jdG���S���T�wg�Ca��.��,�ń����I�UI�2n��H���1��>��痿h<0_���T��=~����/O�_~���맧����{�n��G���gs��k>K�"r�s�Y|i�..G�����𥛻��Cp��Jv�*D���"� Hk�̑�ܵ_���H��@�h#E��!��q#�Ҷ����\|)W�RXB��*�HZi��W���M�J�ͼMW3�8JO#R��:�KE�)���J���/8n md�4W�`��HڭW���K��m|��ЅN{�~_j]�}�Jmw��*�G����l���`Ny�_�l̚r��VS���]U�G��4�aH�ys��rpO$~y�n��&�9����DX�]a	J�ʺUJ�Q�$sӬ���#���Keե��Ԃ�n�T4o�� �%����a�D�.ɹ����|R�\
�t�g��ފHI��;Xίz#�(����J�7��ˑ_�єM�(��8u�a ۦAe}��-�y��6tj�G�G�I����;��o����p�2��LW��\�^L��s�M��<ma�,܏�&'25�s�|U�V������6��=yj��^���z�e8
�g ����U��n�J7q�V�wD��(������j���Ǘ�>���k{��tjy���VT��m�[(�T4�f�kSG�`�}�/���[>쁝�##>.��tF|H! ���s*"�$�9 čT���K�y;B,
!8p��Ȥ�TT��C-���42	�]�y#���p(O�6�i��;>l8�!n��-��B�U��y��h��$Ij ���*E�ȵY�R�/W)\����
Pۀ�t�����'
��6?��v�[�k<����hMdф�C,�.f�3�4R	g��"�|h��F�6א����*�Tfȇ�Ju���n�oA������5hq��i�ⓥJ�]��� �zs릸(�)��07�aN!�|�
yS��u�����������釩�Zj!�Z#J���v���Х,����}���-]u��nۯf]��h��qD�hJ��s�g�t���i9��ͬ1WS&�4�U�J�#��	M���[�U;�~��~�Q�7R��VU�
��o�Y��-G�f����M�zT�ynֳM��r�=����oҡ^�h/F��mgԫ �����իzPI!��kM�o���N+z[8��S�B鎢uN����_V���H�q��#ܔn棷M�)�
�n�|��L������I��Ҏ�	V�;֢Vt��c�5F\]ޓH���k�JM7(#X�Aj�x��~}Q�yJQ��/(�m��:q�h�(\�e�R�A��-k0k����I)-���f�� #$��o$�����0��HmETVb�=�s
��q7��<�9�7�#ˈ��Z"�a��+O%	�v��d�,����zP$@��fR��b�6�g�{�=� �LVr7�w�@�
~��~�i�tmw��_��������      �	   i   x�E�=�0@�9>EO`���[�X�Qɏl���[XX�>���g��h�D�&�ķP�o�*�M�H�-\,�T4���Ъ����k�Y�"��z���� �IS(      �	   d  x��Tٶ�8}Ư�^o$|*��2��_ �@$��?�W�5jy�ʾ�֪��r���}��).E��7��A�[�y��lڳ��?�D�\�����ax���X��%�������'}f����<�'��ID��aJ&,��f��㸗M18#�%%UQ!}��IK!�T�m���s#tV��w����kK�+�ʮ�/��YNFyB1����(q�8{R�.�hM��$��v���ǲ����E}g��Kw��iN@&��$�-���� ⢤�$�ew��3N<�su���L�]�ܾ���h���%��j��]kX~p��qw199[c�?jr�nXv>�X�1���؊S�]���=}�j{ٙ���C�y�=�˼��ٸ�Y�i��=�(�S�TP�k^�!H�O��������ɍɝ!�Dm2�]+t���o/�B,b�X���P�z  K�HN Hh7�֖bZ���\�]��_¥�m������a�����{�����t5#�`��	�fZc��w��c��ij8�À0��x�LS�N�[���2#)�+D횧_(�o,�d�;,X	~G@��D2�:��$O�*M�����[N�{j�%���`,W�0K�P�
�q��xW��cf�dÄ0���/�]V�Pf���]��v���+S�ɢ� v8��}g���e�m��S\yђ�&�{�VmI&�r��x]5��:��Uo��󞟀y�U�B޺XCh�a������)��ZZ���N�q`�d:Fe��n�?Ew�=;������a�/ �nj���3e "��۝����#� N�+E��F��e9��
�Cw8��So��2ُ��p�Cf����,n�۔���M��0�⡲7}��H"�_�׊#�D\�;P�?�s�_���Tq�ԅg� �֫|��E!/�<���:�v�A2��*�����*-Ǧ�L�3����f8�����+ބ���L*�y�w��B�p�9a�#H��m���d�jǗ�ڎ\�Ǆ��EO��tV#���|�{q�ǉnx�"U�dۙ뇢�P��I�r鴃qݻF�Q�Up�nL��/t�z'���W?]K��*��������������s�2�~���4�	yrU      �	   g   x�3���q�wt����2�pB�]����%��$L��FP=��!�.��~!�\���PA�`�`� .SsTCKSL} Q#LQK4�^�A�.�@G������ ��6Z      �	   >   x�3�00����KMTHIU((*MMJ�4���4175�25J�&)�+1�4i������ �&�      
   	  x�}S�n�0<�_�c{p����X$A�6X��m/��u�ʒ ��YE?!?Vʯ���7���C:Ϫkأ����щ׿
�nt�?|'�P�0��C�U��xQt{��e_�0H�S>5̹mk�?ל��ဪ�<���Qpf!5sj�'���xO�2�.`�;��^���"�	�'�����R����y�WD��YV�EШ૮�5���vN�EN���n�Jk�����iIC��t�WFe��p#��v�-�Iq�.{��eFѭ�-[S�ӘK�0����h�������PM��V��g������0`�c�	]��s��d<���8x������r���Nx�-NG2�g�����!V�&'Fvn���2�n���y/l�{d֝�3�J�C�~���u�
=��^-S�鷵Y�P�����q�	5=��-�0z��3g�+�����O!�f�)��Q����[�W����[(� J ��b�!�����j q�
)�n��,����o���7cvMɣ�(���{<      �	   Y   x�3455�I-�,V�4450�220�$'�^�4200�t�,���ML�Gq�d�&*��*x敥�d�'&gޜQ���� (�/      �	   �  x��U�nG<�_�G �"� >�	��luiζ�fg63������}� 폹zfI�2��ywuUw�l~�ft�ߵ�t��&<��kg��u�$dBl���K"[7:�P%z����$���$�8,�Z"���Mp���.r�	�!�_�l���n����5�c�`U�M�ָP7N�p��S��ro$
�i�
�5�8Z^8�iķ�uT�(<Ǫs�W�%���͎@ϛ�?�������h6�O���[������{|8��uk� 7�AE����|B��<,��������C��S�q�fZv��j⥒x���ǌr:�$��ŏ>w$�����Q���4=<D@�;��DD=�p�Op��,]�5!ϳ[�X�d��,��(+�d85*`�(NVZ)|1!axkB5䬂���'��:�"�DK�{Q�������Ug�~�����~g5W�ŋ�y�0�h��S��C� '�-B�8N��~#Z:��i����Y@f(�7���.��j�*c��i��r
$$ke�����w�ҫ@�٦�,�^��w�i����>�Ї�tY����.3MMAV�N�z���F��7�8�E�^�n��/�{� �,2��p�������fY��$�֐��!s�mT�2-ob�I�����6����K����菡n��R��hK\7R�G���
���*����U�ՠ���B~O���\Q�T<���Ԅd3&����_Z�i����Z�2T��o�ܮ��&���l�@t���V2���������l�T�3��A��
_R%g��44l ��=�u�,�3��\�L"�6���x���@� ��5�=�/�r�s��~�h�Or-����R�����%x#�r��-�c0�A��^�P#��C�*����=��*�T��A�[
ZWq%�����eth�Y��lPϚy�1�Eق�$8�;�i�O|0��q�e"wS2[�k*�b�?�����7WGY      �	   �   x�}�=�0�g�=���i'f$���%�Ia@ ��i#T������=+�Z��?�{u�j{����*��z9�츠��+���dP�"[-��[RiقӖ�z`f��"���:���`��PQ�ZC)I9���vAEBah*Y���LK9��p8���%5J9�rdk@� i+��,�O�9��S*5@��C�a����.Q=~�~e�y��t�      �	   �   x���n�0Eg�+�)������4ݺ��E@�
�__u�ӹ��a<��a�pn9T��dYv�v}��9m��o�XR����*>�(��Fq˼j ����Wk9�T���]�ٸD|���.�>�gM��բ$��Jq�N�gB�/�V.�R_a:Mo4�63�ւ�{�E�gw���"��lH�      �	   �   x�5�M� ����@m�_�Fwc�n(��H���BO��@B¼�f^ޔYۛ��	�}"(^K�'�\�hB	���+*��� 4N��3������Ԛ�X�Y�ܐl�mR[���&	��d�	�7N�3d�=�s����H~�fr ��ѻ��>�P�/��,W�mz��9k>Q[�	�[�i�(���T�      �	   s  x��X[n�F��W1_EH4�z(
�qR��&A��1g(�!g�J���t]D���+����ő�|U %���=�����-�ȒPpI�4	.Q��l<I�@s$s���\�ت�6�Ѐ1N��Oq��Uo ����}����k���Lpyd\�3d"�a�E	���0@`���oFE��q_',�,>5�IA,�?j'R@�����Sߎ���^�"�H�N`=8��	��(<�PY���۶��K`L]K�l$�7*�,����T@��� X�Q��&=�mPk�i�w�,iYDaF��oiMB����-k��JS�QV���-/l�-ԞQ��рc�-O��E�˓���lfF8�7�5C��D��3n�ƈ���X�յ]��8����Wh�("��� �X���?��ib�h屌���5�F���ܫ�ծ�"\�S]�-s�������o��3�ncŜ�L�[,(����vY���9��	Fkk�V5��a���l�rMW�\� �#$"��,�V�`_���LH���ă��0�ޮ4�P-m��m?�L�}8u*�tۤ��}}��{�hh�<�� ZF�-�����Y(���m�-�X�Y|=q�?�F-J���
k9iQ@q<��lh����E��n2,��VVk��=� eXo�\WOs>)&7�O3\���ӟ^~����j���.�D���v����]8�}�:u�Mծ�;����{��PJu+��U����c-�t� ]
�J�$e�5�����W�+Ȋ ���k�V�Ԧ_��@>���@{�POɫ"\���|Z"��a-���{�Éz2��c���6�\+¡v���ft���ß��_��Q�5ѩ�~��C����Ѱ��S8�$e�ԌF���@��H&㔣�k�:{��>\��;;}��b�AW�=0x�
AQA�H�#��p"$��
��������V^�|�	��������l��$h��,���H��p�}�]��Y���8�奿�8lJ��z��-f�3����$ì��M��D?�YR��	x�ω�B��*�O�f���4�SZЀÅ �"�(�E�	�و
1�rZ����g.�B��H�k�ML��*N�$�;�L�5 g�]7q�"AVØ%��hLQ���K1gP3��O�ǘr����� Qi
��y��w+٦܈�;�����O�W妷j���m��8L���� j7��1�S��}GV?�M�r��i�ZZ����{�������\��ĺ�n#���tG����{�UL���
&��:�ރ����{[RA��!fA��R�E� I���,g���ոJ$z�\oP�����r0z�K`>W�҄Q�4Ҧ*\{9b6�>� ��׳�Q`�a��      �	   g  x�]�ݎ1���S��������"�E�Y�\p�&ƚ�9��ӯ��T�M��8��Fc��q�R��pW��a:����Ρ��SMʰ_�M�QRVq�W;\ƨ^�C�<�j��H�'q�n38j<(uO�*�Q�y�*q�!�,qͮ��s���X��NB�ÄC2�z�g�b첄�&�`���r/�2�s���%��Ϡ���	��|2��J`\���*����?��%��^�?���K�f�v�-g�3v�G:y��+���$-�����˚D�
^���ڱʌ��m.m�b�����*��t�x�z)��1�Ċ�����R���$��mٓ��d϶7�a���M��2������k      �	   �   x�M��j�0@�����u�=�����+���j+� ���t��Ü�v��6j���|��U�Zթ�~y�M��a�6�2a��{=29�ɟ\go��)zKfQ)��w�?�����b�tmL���=r�Ⱦ{��Zmɂ2+|��q�%�O8��c��g#3_�@r���Ƨ������K����o���l���xs��F�I�w�W8^UU��xoO      �	   5   x�3�040���/J�U�,().�U �54�j1�2j5B�$ąh5����� H$�      �	   �  x�uR�n�0>3O��`+Nl�n�rH7 ݭ�b-���I���.�C�@	����9�C����Zn#���>(�o 2�m3�ͪ۲yU��s:w70�������[DU�݂�%���9<�1*|b�;MHVbT����uҭ�c0��Pl��:���:�6��\g^d�XXh���l���p%�ꢪ�D�ʍ��^�ĿBW�:��~�d�~��m|2�
I/����ѻw�бeO�����D3~�n��
��PU��S�m�IG%=Md���i-������ff�2���(I9x�8��φ������&3�Y[dy�/�k�:[�:譛ˎ���x?�}��hJ{^�Q��Ce��g�r6A.�10�_S]��.ʏ	�~�l6^kκ      
   c  x�}��n�0Eg�c
��,g��c+Э_��G�P�ׄ�CZ�����M�ӏ?�����qۮS��3/����Y�[)UY<Ύ���e�ҪA���C�:e����s�P�̞�f��Z�b	1m-Ćbڊ���8g�3��eO�Clh��$,!"�� 6Tm�
�!��	)��KPh<����n.�!�� 4�pU��-���и����%lH<
AC��τ��[���X
�A^����y�f�*�Y
���>�0�u0cf�`A,��(Ð�(�p&���v�?N�������$���*̢pVa.E�
3*-�0��dfWzVa�eݯ�r�*,m+Y��m�Ӹm���j�w��z����m��      �	   �   x�m�1�0Eg�=�M\XX8 K��	Q�I�OD[�����d�N�2��:����L�ic��F��hހ���B��ː��`ѿr(��iu/�NX�A�D�U�JV�/�/�����G�64�v��� H�      �	   �   x�=�=�0���>EN���P1"U*bb1i@FmR��q�9B/�U�'��5l�-7>��*�)�)XAnC�*Þ�b#N���K���*���8�-%�*��m͎c
>�
nlX�]�2���s��cņdw}��������,7���I*\B杨��V��r+��%��5���o����� ?�tL�      �	   l  x�]��N�0�י��p4��s1HÒ��xZkҤr����ED������,�:�#{���b䚒'�~�	a�S�#����r��_�W�k���4Î�sG��Oŵ9P��YQ���D%+N���SiX^�Z��J'�kwt;S����$��L��B���+V��0$������U$�@��P
������0j/9�p� j�K˪��F��	��bv��X��Ua�]��p<Y`�}���|a�����ϽnM�O^(�X$P���6�O#�e��n��!'X�'�ݍ�[����P��m܅�	�{r��vC�O��r-�E{˷�¾8j+AO㗱:��K�v����f�7�@      �	   2   x�3160�(*MMJT(�,�WHIU((JM/�+I�,@OI,������ �C      
   '  x�e��N�@Eמ���x?�m�P�j�wb��x43I�w+/||��5��[׸�~j:���V<�R���S���h�p�uH��K�|	�s�e(�u�p(��Y�5G�R�"	�J�����8Q��`E���3�u0nj���!2CK���y�5�{�S��,?vU�T2�q�(���=@�̆
�"��X�j��a }4	����جq-$Y�{S�t�Hә��Z��Kl�p��4�'XtZ��'��$��=Ü�����^�ō�l���
bҢ^;��8kϰ;v�jO���9�冐�     