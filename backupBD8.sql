toc.dat                                                                                             0000600 0004000 0002000 00000231350 13767147462 0014464 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP           0    
            x         	   ciecyt_db    11.6    11.6 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false         �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false         �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false         �           1262    52041 	   ciecyt_db    DATABASE     �   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';
    DROP DATABASE ciecyt_db;
             postgres    false         �            1259    52042    acuerdo    TABLE     �   CREATE TABLE public.acuerdo (
    id bigint NOT NULL,
    acuerdo character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.acuerdo;
       public         ciecyt_admin    false         �            1259    52048    adjunto_proyecto_fase    TABLE     �  CREATE TABLE public.adjunto_proyecto_fase (
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
       public         ciecyt_admin    false         �            1259    52054    adjunto_retroalimentacion    TABLE     �  CREATE TABLE public.adjunto_retroalimentacion (
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
       public         ciecyt_admin    false         �            1259    52060    categorizacion    TABLE     �   CREATE TABLE public.categorizacion (
    id bigint NOT NULL,
    categoria character varying(255),
    descripcion character varying(255),
    categorizacion_proyecto_id bigint
);
 "   DROP TABLE public.categorizacion;
       public         ciecyt_admin    false         �            1259    52066    ciclo_propedeutico    TABLE     �   CREATE TABLE public.ciclo_propedeutico (
    id bigint NOT NULL,
    ciclo character varying(255),
    modalidad_id bigint,
    ciclo_propedeutico_acuerdo_id bigint
);
 &   DROP TABLE public.ciclo_propedeutico;
       public         ciecyt_admin    false         �            1259    52069 
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
       public         ciecyt_admin    false         �            1259    52072    cronograma_ciecyt    TABLE     �   CREATE TABLE public.cronograma_ciecyt (
    id bigint NOT NULL,
    titulo_cronograma character varying(255),
    fecha_inicio date,
    fecha_fin date,
    observaciones character varying(255),
    cronograma_ciecyt_modalidad_id bigint
);
 %   DROP TABLE public.cronograma_ciecyt;
       public         ciecyt_admin    false         �            1259    52078    cronograma_ciecyt_fases    TABLE       CREATE TABLE public.cronograma_ciecyt_fases (
    id bigint NOT NULL,
    inicio_fase date,
    fin_fase date,
    texto_explicativo character varying(255),
    cronograma_ciecyt_fases_cronograma_ciecyt_id bigint,
    cronograma_ciecyt_fases_fases_id bigint
);
 +   DROP TABLE public.cronograma_ciecyt_fases;
       public         ciecyt_admin    false         �            1259    52081    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
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
       public         ciecyt_admin    false         �            1259    52087    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public         ciecyt_admin    false         �            1259    52090    elemento    TABLE     �   CREATE TABLE public.elemento (
    id bigint NOT NULL,
    elemento character varying(255),
    elemento_formato_id bigint,
    elemento_modalidad_id bigint,
    descripcion text
);
    DROP TABLE public.elemento;
       public         ciecyt_admin    false         �            1259    52096    elemento_proyecto    TABLE     �   CREATE TABLE public.elemento_proyecto (
    id bigint NOT NULL,
    dato text,
    elemento_proyecto_elemento_id bigint,
    elemento_proyecto_proyecto_id bigint,
    elemento_proyecto_proyecto_descripcion text
);
 %   DROP TABLE public.elemento_proyecto;
       public         ciecyt_admin    false         �            1259    52102    entidad    TABLE     |   CREATE TABLE public.entidad (
    id bigint NOT NULL,
    entidad character varying(255),
    nit character varying(255)
);
    DROP TABLE public.entidad;
       public         ciecyt_admin    false         �            1259    52108    entidad_financiadora    TABLE     �   CREATE TABLE public.entidad_financiadora (
    id bigint NOT NULL,
    valor double precision,
    aprobada boolean,
    entidad_financiadora_entidad_id bigint,
    entidad_financiadora_proyecto_id bigint
);
 (   DROP TABLE public.entidad_financiadora;
       public         ciecyt_admin    false         �            1259    52111    facultad    TABLE     �   CREATE TABLE public.facultad (
    id bigint NOT NULL,
    codigo_facultad character varying(255),
    facultad character varying(255)
);
    DROP TABLE public.facultad;
       public         ciecyt_admin    false         �            1259    52117    fases    TABLE     �   CREATE TABLE public.fases (
    id bigint NOT NULL,
    fase character varying(255),
    notificable boolean,
    fases_modalidad_id bigint
);
    DROP TABLE public.fases;
       public         ciecyt_admin    false         �            1259    52120    ficha_tecnica    TABLE     �   CREATE TABLE public.ficha_tecnica (
    id bigint NOT NULL,
    titulo_profesional character varying(255),
    titulo_postgrado character varying(255),
    experiencia character varying(255),
    ficha_tecnica_user_id bigint
);
 !   DROP TABLE public.ficha_tecnica;
       public         ciecyt_admin    false         �            1259    52126    formato    TABLE     �   CREATE TABLE public.formato (
    id bigint NOT NULL,
    formato character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.formato;
       public         ciecyt_admin    false         �            1259    52132    grupo_semillero    TABLE     u   CREATE TABLE public.grupo_semillero (
    id bigint NOT NULL,
    nombre character varying(255),
    tipo boolean
);
 #   DROP TABLE public.grupo_semillero;
       public         ciecyt_admin    false         �            1259    52135    impactos_esperados    TABLE     	  CREATE TABLE public.impactos_esperados (
    id bigint NOT NULL,
    impacto character varying(255),
    plazo integer,
    indicador character varying(255),
    supuestos character varying(255),
    impactos_esperado_proyecto_id bigint,
    orden_vista integer
);
 &   DROP TABLE public.impactos_esperados;
       public         ciecyt_admin    false         �            1259    52141    informacion_pasantia    TABLE     �  CREATE TABLE public.informacion_pasantia (
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
    informacion_pasantia_proyecto_id bigint,
    convenio text
);
 (   DROP TABLE public.informacion_pasantia;
       public         ciecyt_admin    false         �            1259    52147    integrante_proyecto    TABLE       CREATE TABLE public.integrante_proyecto (
    id bigint NOT NULL,
    integrante character varying(255),
    descripcion character varying(255),
    integrante_proyecto_user_id bigint,
    integrante_proyecto_proyecto_id bigint,
    integrante_proyecto_roles_modalidad_id bigint
);
 '   DROP TABLE public.integrante_proyecto;
       public         ciecyt_admin    false         �            1259    60801    investigacion_tipo    TABLE     �   CREATE TABLE public.investigacion_tipo (
    id bigint NOT NULL,
    investigacion_tipo text,
    investigacion_tipo_descripcion text,
    tipo text,
    tipo_descripcion text
);
 &   DROP TABLE public.investigacion_tipo;
       public         postgres    false         �            1259    60799    investigacion_tipo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.investigacion_tipo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.investigacion_tipo_id_seq;
       public       postgres    false    243         �           0    0    investigacion_tipo_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.investigacion_tipo_id_seq OWNED BY public.investigacion_tipo.id;
            public       postgres    false    242         �            1259    52153    jhi_authority    TABLE     O   CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);
 !   DROP TABLE public.jhi_authority;
       public         ciecyt_admin    false         �            1259    52156    jhi_persistent_audit_event    TABLE     �   CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);
 .   DROP TABLE public.jhi_persistent_audit_event;
       public         ciecyt_admin    false         �            1259    52159    jhi_persistent_audit_evt_data    TABLE     �   CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);
 1   DROP TABLE public.jhi_persistent_audit_evt_data;
       public         ciecyt_admin    false         �            1259    52162    jhi_user    TABLE     �  CREATE TABLE public.jhi_user (
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
       public         ciecyt_admin    false         �            1259    52168    jhi_user_authority    TABLE     {   CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);
 &   DROP TABLE public.jhi_user_authority;
       public         ciecyt_admin    false         �            1259    52171    linea_investigacion    TABLE     �   CREATE TABLE public.linea_investigacion (
    id bigint NOT NULL,
    linea character varying(255),
    codigo_linea character varying(255),
    linea_padre_id bigint,
    linea_investigacion_programa_id bigint
);
 '   DROP TABLE public.linea_investigacion;
       public         ciecyt_admin    false         �            1259    52177    menu    TABLE     �   CREATE TABLE public.menu (
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
       public         ciecyt_admin    false         �            1259    52183 	   modalidad    TABLE     �   CREATE TABLE public.modalidad (
    id bigint NOT NULL,
    modalidad character varying(255),
    modalidad_acuerdo_id bigint,
    contiene_linea boolean
);
    DROP TABLE public.modalidad;
       public         ciecyt_admin    false         �            1259    52186    pregunta    TABLE     y  CREATE TABLE public.pregunta (
    id bigint NOT NULL,
    encabezado character varying(255),
    descripcion text,
    pregunta text,
    pregunta_tipo_pregunta_id bigint,
    pregunta_modalidad_id bigint,
    pregunta_roles_modalidad_id bigint,
    pregunta_elemento text,
    pregunta_elemento_id bigint,
    puntaje double precision,
    puntaje_maximo double precision
);
    DROP TABLE public.pregunta;
       public         ciecyt_admin    false         �            1259    52192    presupuesto_valor    TABLE     �  CREATE TABLE public.presupuesto_valor (
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
       public         ciecyt_admin    false         �            1259    52198    producto    TABLE     ^   CREATE TABLE public.producto (
    id bigint NOT NULL,
    producto character varying(255)
);
    DROP TABLE public.producto;
       public         ciecyt_admin    false         �            1259    52201    producto_proyecto    TABLE     �   CREATE TABLE public.producto_proyecto (
    id bigint NOT NULL,
    aplica boolean,
    descripcion character varying(255),
    producto_proyecto_producto_id bigint,
    producto_proyecto_proyecto_id bigint
);
 %   DROP TABLE public.producto_proyecto;
       public         ciecyt_admin    false         �            1259    60813    programa    TABLE       CREATE TABLE public.programa (
    id bigint NOT NULL,
    programa text,
    descripcion text,
    codigo_interno text,
    codigo_snies text,
    creditos integer,
    ciclo text,
    resolucion text,
    titulo text,
    duracion_semestres integer,
    programa_facultad_id bigint
);
    DROP TABLE public.programa;
       public         postgres    false         �            1259    60811    programa_id_seq    SEQUENCE     x   CREATE SEQUENCE public.programa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.programa_id_seq;
       public       postgres    false    245         �           0    0    programa_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id;
            public       postgres    false    244         �            1259    52204    proyecto    TABLE     �  CREATE TABLE public.proyecto (
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
    referencias text,
    proyecto_programa_id bigint,
    programa text,
    departamento character varying(255),
    municipio character varying(255),
    viable boolean,
    enviado boolean,
    fecha_envio_propuesta timestamp with time zone,
    fecha_envio_proyecto timestamp with time zone,
    nota double precision,
    conclusion text,
    recomendaciones text
);
    DROP TABLE public.proyecto;
       public         ciecyt_admin    false         �            1259    52210    proyecto_fase    TABLE       CREATE TABLE public.proyecto_fase (
    id bigint NOT NULL,
    titulo character varying(255),
    cumplida boolean,
    fecha_cumplimiento date,
    observaciones character varying(255),
    proyecto_fase_fases_id bigint,
    proyecto_fase_proyecto_id bigint
);
 !   DROP TABLE public.proyecto_fase;
       public         ciecyt_admin    false         �            1259    52216    proyecto_respuestas    TABLE     a  CREATE TABLE public.proyecto_respuestas (
    id bigint NOT NULL,
    respuesta character varying(255),
    observaciones character varying(255),
    viable boolean,
    proyecto_respuestas_pregunta_id bigint,
    proyecto_respuestas_proyecto_id bigint,
    proyecto_respuestas_pregunta_pregunta text,
    puntaje double precision,
    si_no boolean
);
 '   DROP TABLE public.proyecto_respuestas;
       public         ciecyt_admin    false         �            1259    52222    resultados_esperados    TABLE        CREATE TABLE public.resultados_esperados (
    id bigint NOT NULL,
    resultado character varying(255),
    indicador character varying(255),
    beneficiario character varying(255),
    resultados_esperados_proyecto_id bigint,
    orden_vista integer
);
 (   DROP TABLE public.resultados_esperados;
       public         ciecyt_admin    false         �            1259    52228    retroalimentacion    TABLE     O  CREATE TABLE public.retroalimentacion (
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
       public         ciecyt_admin    false         �            1259    52234    rol_menu    TABLE     �   CREATE TABLE public.rol_menu (
    id bigint NOT NULL,
    permitir_acceso boolean,
    permitir_crear boolean,
    permitir_editar boolean,
    permitir_eliminar boolean,
    auth_name character varying(255),
    rol_menu_menu_id bigint
);
    DROP TABLE public.rol_menu;
       public         ciecyt_admin    false         �            1259    52237    roles_modalidad    TABLE     �   CREATE TABLE public.roles_modalidad (
    id bigint NOT NULL,
    rol character varying(255),
    cantidad integer,
    calificador boolean,
    roles_modalidad_modalidad_id bigint,
    roles_modalidad_authority_name character varying(60)
);
 #   DROP TABLE public.roles_modalidad;
       public         ciecyt_admin    false         �            1259    52240    rubro    TABLE     X   CREATE TABLE public.rubro (
    id bigint NOT NULL,
    rubro character varying(255)
);
    DROP TABLE public.rubro;
       public         ciecyt_admin    false         �            1259    52243    sequence_generator    SEQUENCE        CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sequence_generator;
       public       ciecyt_admin    false         �            1259    52245 	   solicitud    TABLE     �   CREATE TABLE public.solicitud (
    id bigint NOT NULL,
    estado boolean,
    asunto character varying(255),
    texto_solicitud character varying(255),
    fecha_solicitud date,
    solicitud_integrante_proyecto_id bigint
);
    DROP TABLE public.solicitud;
       public         ciecyt_admin    false         �            1259    52251    tipo_pregunta    TABLE     �   CREATE TABLE public.tipo_pregunta (
    id bigint NOT NULL,
    tipo_pregunta character varying(255),
    tipo_dato character varying(255)
);
 !   DROP TABLE public.tipo_pregunta;
       public         ciecyt_admin    false         �            1259    52257    usuario    TABLE     �   CREATE TABLE public.usuario (
    id bigint NOT NULL,
    usuario character varying(255),
    descripcion character varying(255)
);
    DROP TABLE public.usuario;
       public         ciecyt_admin    false         X           2604    60804    investigacion_tipo id    DEFAULT     ~   ALTER TABLE ONLY public.investigacion_tipo ALTER COLUMN id SET DEFAULT nextval('public.investigacion_tipo_id_seq'::regclass);
 D   ALTER TABLE public.investigacion_tipo ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    242    243    243         Y           2604    60816    programa id    DEFAULT     j   ALTER TABLE ONLY public.programa ALTER COLUMN id SET DEFAULT nextval('public.programa_id_seq'::regclass);
 :   ALTER TABLE public.programa ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    244    245    245         g          0    52042    acuerdo 
   TABLE DATA               F   COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    196       3175.dat h          0    52048    adjunto_proyecto_fase 
   TABLE DATA               �   COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM stdin;
    public       ciecyt_admin    false    197       3176.dat i          0    52054    adjunto_retroalimentacion 
   TABLE DATA               �   COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM stdin;
    public       ciecyt_admin    false    198       3177.dat j          0    52060    categorizacion 
   TABLE DATA               `   COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    199       3178.dat k          0    52066    ciclo_propedeutico 
   TABLE DATA               d   COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM stdin;
    public       ciecyt_admin    false    200       3179.dat l          0    52069 
   cronograma 
   TABLE DATA               {   COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    201       3180.dat m          0    52072    cronograma_ciecyt 
   TABLE DATA               �   COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    202       3181.dat n          0    52078    cronograma_ciecyt_fases 
   TABLE DATA               �   COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM stdin;
    public       ciecyt_admin    false    203       3182.dat o          0    52081    databasechangelog 
   TABLE DATA               �   COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    public       ciecyt_admin    false    204       3183.dat p          0    52087    databasechangeloglock 
   TABLE DATA               R   COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    public       ciecyt_admin    false    205       3184.dat q          0    52090    elemento 
   TABLE DATA               i   COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM stdin;
    public       ciecyt_admin    false    206       3185.dat r          0    52096    elemento_proyecto 
   TABLE DATA               �   COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id, elemento_proyecto_proyecto_descripcion) FROM stdin;
    public       ciecyt_admin    false    207       3186.dat s          0    52102    entidad 
   TABLE DATA               3   COPY public.entidad (id, entidad, nit) FROM stdin;
    public       ciecyt_admin    false    208       3187.dat t          0    52108    entidad_financiadora 
   TABLE DATA               �   COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id, entidad_financiadora_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    209       3188.dat u          0    52111    facultad 
   TABLE DATA               A   COPY public.facultad (id, codigo_facultad, facultad) FROM stdin;
    public       ciecyt_admin    false    210       3189.dat v          0    52117    fases 
   TABLE DATA               J   COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    211       3190.dat w          0    52120    ficha_tecnica 
   TABLE DATA               u   COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM stdin;
    public       ciecyt_admin    false    212       3191.dat x          0    52126    formato 
   TABLE DATA               F   COPY public.formato (id, formato, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    213       3192.dat y          0    52132    grupo_semillero 
   TABLE DATA               ;   COPY public.grupo_semillero (id, nombre, tipo) FROM stdin;
    public       ciecyt_admin    false    214       3193.dat z          0    52135    impactos_esperados 
   TABLE DATA               �   COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    215       3194.dat {          0    52141    informacion_pasantia 
   TABLE DATA               Y  COPY public.informacion_pasantia (duracion_horas, direccion, email, lunes, martes, miercoles, jueves, viernes, sabado, domingo, bono_alimenticio, apoyo_economico, auxilio_transporte, capacitacion, otro_apoyo, nombre_empresa, nit_empresa, direccion_empresa, sector_economico_empresa, representante_legal_empresa, asesor_empresa, cargo_asesor_empresa, email_asesor_empresa, municipio_empresa, telefono_contacto_empresa, email_empresa, departamento_empresa, identificacion_representante_legal, profesion_asesor_empresa, celular_asesor_empresa, id, informacion_pasantia_proyecto_id, convenio) FROM stdin;
    public       ciecyt_admin    false    216       3195.dat |          0    52147    integrante_proyecto 
   TABLE DATA               �   COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    217       3196.dat �          0    60801    investigacion_tipo 
   TABLE DATA               |   COPY public.investigacion_tipo (id, investigacion_tipo, investigacion_tipo_descripcion, tipo, tipo_descripcion) FROM stdin;
    public       postgres    false    243       3222.dat }          0    52153    jhi_authority 
   TABLE DATA               -   COPY public.jhi_authority (name) FROM stdin;
    public       ciecyt_admin    false    218       3197.dat ~          0    52156    jhi_persistent_audit_event 
   TABLE DATA               a   COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
    public       ciecyt_admin    false    219       3198.dat           0    52159    jhi_persistent_audit_evt_data 
   TABLE DATA               N   COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
    public       ciecyt_admin    false    220       3199.dat �          0    52162    jhi_user 
   TABLE DATA               �   COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
    public       ciecyt_admin    false    221       3200.dat �          0    52168    jhi_user_authority 
   TABLE DATA               E   COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
    public       ciecyt_admin    false    222       3201.dat �          0    52171    linea_investigacion 
   TABLE DATA               w   COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_programa_id) FROM stdin;
    public       ciecyt_admin    false    223       3202.dat �          0    52177    menu 
   TABLE DATA               `   COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM stdin;
    public       ciecyt_admin    false    224       3203.dat �          0    52183 	   modalidad 
   TABLE DATA               X   COPY public.modalidad (id, modalidad, modalidad_acuerdo_id, contiene_linea) FROM stdin;
    public       ciecyt_admin    false    225       3204.dat �          0    52186    pregunta 
   TABLE DATA               �   COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, pregunta_elemento, pregunta_elemento_id, puntaje, puntaje_maximo) FROM stdin;
    public       ciecyt_admin    false    226       3205.dat �          0    52192    presupuesto_valor 
   TABLE DATA               �   COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    227       3206.dat �          0    52198    producto 
   TABLE DATA               0   COPY public.producto (id, producto) FROM stdin;
    public       ciecyt_admin    false    228       3207.dat �          0    52201    producto_proyecto 
   TABLE DATA               �   COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    229       3208.dat �          0    60813    programa 
   TABLE DATA               �   COPY public.programa (id, programa, descripcion, codigo_interno, codigo_snies, creditos, ciclo, resolucion, titulo, duracion_semestres, programa_facultad_id) FROM stdin;
    public       postgres    false    245       3224.dat �          0    52204    proyecto 
   TABLE DATA               �  COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias, proyecto_programa_id, programa, departamento, municipio, viable, enviado, fecha_envio_propuesta, fecha_envio_proyecto, nota, conclusion, recomendaciones) FROM stdin;
    public       ciecyt_admin    false    230       3209.dat �          0    52210    proyecto_fase 
   TABLE DATA               �   COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    231       3210.dat �          0    52216    proyecto_respuestas 
   TABLE DATA               �   COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id, proyecto_respuestas_pregunta_pregunta, puntaje, si_no) FROM stdin;
    public       ciecyt_admin    false    232       3211.dat �          0    52222    resultados_esperados 
   TABLE DATA               �   COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    233       3212.dat �          0    52228    retroalimentacion 
   TABLE DATA               �   COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM stdin;
    public       ciecyt_admin    false    234       3213.dat �          0    52234    rol_menu 
   TABLE DATA               �   COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM stdin;
    public       ciecyt_admin    false    235       3214.dat �          0    52237    roles_modalidad 
   TABLE DATA               �   COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id, roles_modalidad_authority_name) FROM stdin;
    public       ciecyt_admin    false    236       3215.dat �          0    52240    rubro 
   TABLE DATA               *   COPY public.rubro (id, rubro) FROM stdin;
    public       ciecyt_admin    false    237       3216.dat �          0    52245 	   solicitud 
   TABLE DATA               {   COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    239       3218.dat �          0    52251    tipo_pregunta 
   TABLE DATA               E   COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM stdin;
    public       ciecyt_admin    false    240       3219.dat �          0    52257    usuario 
   TABLE DATA               ;   COPY public.usuario (id, usuario, descripcion) FROM stdin;
    public       ciecyt_admin    false    241       3220.dat �           0    0    investigacion_tipo_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.investigacion_tipo_id_seq', 1, true);
            public       postgres    false    242         �           0    0    programa_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.programa_id_seq', 1, true);
            public       postgres    false    244         �           0    0    sequence_generator    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sequence_generator', 31450, true);
            public       ciecyt_admin    false    238         [           2606    52265    acuerdo acuerdo_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.acuerdo
    ADD CONSTRAINT acuerdo_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.acuerdo DROP CONSTRAINT acuerdo_pkey;
       public         ciecyt_admin    false    196         ]           2606    52267 0   adjunto_proyecto_fase adjunto_proyecto_fase_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_pkey;
       public         ciecyt_admin    false    197         _           2606    52269 8   adjunto_retroalimentacion adjunto_retroalimentacion_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_pkey;
       public         ciecyt_admin    false    198         a           2606    52271 "   categorizacion categorizacion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT categorizacion_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT categorizacion_pkey;
       public         ciecyt_admin    false    199         c           2606    52273 *   ciclo_propedeutico ciclo_propedeutico_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT ciclo_propedeutico_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT ciclo_propedeutico_pkey;
       public         ciecyt_admin    false    200         i           2606    52275 4   cronograma_ciecyt_fases cronograma_ciecyt_fases_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_pkey;
       public         ciecyt_admin    false    203         g           2606    52277 (   cronograma_ciecyt cronograma_ciecyt_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT cronograma_ciecyt_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT cronograma_ciecyt_pkey;
       public         ciecyt_admin    false    202         e           2606    52279    cronograma cronograma_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT cronograma_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT cronograma_pkey;
       public         ciecyt_admin    false    201         k           2606    52281 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public         ciecyt_admin    false    205         m           2606    52283    elemento elemento_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT elemento_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.elemento DROP CONSTRAINT elemento_pkey;
       public         ciecyt_admin    false    206         o           2606    52285 (   elemento_proyecto elemento_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT elemento_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT elemento_proyecto_pkey;
       public         ciecyt_admin    false    207         s           2606    52287 .   entidad_financiadora entidad_financiadora_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT entidad_financiadora_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT entidad_financiadora_pkey;
       public         ciecyt_admin    false    209         q           2606    52289    entidad entidad_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.entidad
    ADD CONSTRAINT entidad_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.entidad DROP CONSTRAINT entidad_pkey;
       public         ciecyt_admin    false    208         u           2606    52291    facultad facultad_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.facultad DROP CONSTRAINT facultad_pkey;
       public         ciecyt_admin    false    210         w           2606    52293    fases fases_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fases_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.fases DROP CONSTRAINT fases_pkey;
       public         ciecyt_admin    false    211         y           2606    52295     ficha_tecnica ficha_tecnica_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT ficha_tecnica_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT ficha_tecnica_pkey;
       public         ciecyt_admin    false    212         {           2606    52297    formato formato_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.formato DROP CONSTRAINT formato_pkey;
       public         ciecyt_admin    false    213         }           2606    52299 $   grupo_semillero grupo_semillero_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.grupo_semillero
    ADD CONSTRAINT grupo_semillero_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.grupo_semillero DROP CONSTRAINT grupo_semillero_pkey;
       public         ciecyt_admin    false    214                    2606    52301 *   impactos_esperados impactos_esperados_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT impactos_esperados_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT impactos_esperados_pkey;
       public         ciecyt_admin    false    215         �           2606    52303 .   informacion_pasantia informacion_pasantia_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.informacion_pasantia
    ADD CONSTRAINT informacion_pasantia_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.informacion_pasantia DROP CONSTRAINT informacion_pasantia_pkey;
       public         ciecyt_admin    false    216         �           2606    52305 ,   integrante_proyecto integrante_proyecto_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT integrante_proyecto_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT integrante_proyecto_pkey;
       public         ciecyt_admin    false    217         �           2606    60809 *   investigacion_tipo investigacion_tipo_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.investigacion_tipo
    ADD CONSTRAINT investigacion_tipo_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.investigacion_tipo DROP CONSTRAINT investigacion_tipo_pkey;
       public         postgres    false    243         �           2606    52307     jhi_authority jhi_authority_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);
 J   ALTER TABLE ONLY public.jhi_authority DROP CONSTRAINT jhi_authority_pkey;
       public         ciecyt_admin    false    218         �           2606    52309 :   jhi_persistent_audit_event jhi_persistent_audit_event_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);
 d   ALTER TABLE ONLY public.jhi_persistent_audit_event DROP CONSTRAINT jhi_persistent_audit_event_pkey;
       public         ciecyt_admin    false    219         �           2606    52311 @   jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);
 j   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT jhi_persistent_audit_evt_data_pkey;
       public         ciecyt_admin    false    220    220         �           2606    52313 *   jhi_user_authority jhi_user_authority_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);
 T   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT jhi_user_authority_pkey;
       public         ciecyt_admin    false    222    222         �           2606    52315    jhi_user jhi_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT jhi_user_pkey;
       public         ciecyt_admin    false    221         �           2606    52317 ,   linea_investigacion linea_investigacion_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT linea_investigacion_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT linea_investigacion_pkey;
       public         ciecyt_admin    false    223         �           2606    52319    menu menu_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public         ciecyt_admin    false    224         �           2606    52321    modalidad modalidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT modalidad_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT modalidad_pkey;
       public         ciecyt_admin    false    225         �           2606    52323    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public         ciecyt_admin    false    226         �           2606    52325 (   presupuesto_valor presupuesto_valor_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT presupuesto_valor_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT presupuesto_valor_pkey;
       public         ciecyt_admin    false    227         �           2606    52327    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public         ciecyt_admin    false    228         �           2606    52329 (   producto_proyecto producto_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT producto_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT producto_proyecto_pkey;
       public         ciecyt_admin    false    229         �           2606    60821    programa programa_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.programa DROP CONSTRAINT programa_pkey;
       public         postgres    false    245         �           2606    52331     proyecto_fase proyecto_fase_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT proyecto_fase_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT proyecto_fase_pkey;
       public         ciecyt_admin    false    231         �           2606    52333    proyecto proyecto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public         ciecyt_admin    false    230         �           2606    52335 ,   proyecto_respuestas proyecto_respuestas_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT proyecto_respuestas_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT proyecto_respuestas_pkey;
       public         ciecyt_admin    false    232         �           2606    52337 .   resultados_esperados resultados_esperados_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT resultados_esperados_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT resultados_esperados_pkey;
       public         ciecyt_admin    false    233         �           2606    52339 (   retroalimentacion retroalimentacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT retroalimentacion_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT retroalimentacion_pkey;
       public         ciecyt_admin    false    234         �           2606    52341    rol_menu rol_menu_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT rol_menu_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT rol_menu_pkey;
       public         ciecyt_admin    false    235         �           2606    52343 $   roles_modalidad roles_modalidad_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT roles_modalidad_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT roles_modalidad_pkey;
       public         ciecyt_admin    false    236         �           2606    52345    rubro rubro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rubro
    ADD CONSTRAINT rubro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rubro DROP CONSTRAINT rubro_pkey;
       public         ciecyt_admin    false    237         �           2606    52347    solicitud solicitud_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT solicitud_pkey;
       public         ciecyt_admin    false    239         �           2606    52349     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public         ciecyt_admin    false    240         �           2606    52351    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         ciecyt_admin    false    241         �           2606    52353    jhi_user ux_user_email 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_email;
       public         ciecyt_admin    false    221         �           2606    52355    jhi_user ux_user_login 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_login;
       public         ciecyt_admin    false    221         �           1259    69007 :   fki_fk_linea_investigacion_linea_investigacion_programa_id    INDEX     �   CREATE INDEX fki_fk_linea_investigacion_linea_investigacion_programa_id ON public.linea_investigacion USING btree (linea_investigacion_programa_id);
 N   DROP INDEX public.fki_fk_linea_investigacion_linea_investigacion_programa_id;
       public         ciecyt_admin    false    223         �           1259    52356    idx_persistent_audit_event    INDEX     r   CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);
 .   DROP INDEX public.idx_persistent_audit_event;
       public         ciecyt_admin    false    219    219         �           1259    52357    idx_persistent_audit_evt_data    INDEX     k   CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);
 1   DROP INDEX public.idx_persistent_audit_evt_data;
       public         ciecyt_admin    false    220         �           2606    52358 R   adjunto_proyecto_fase adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id FOREIGN KEY (adjunto_proyecto_fase_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 |   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id;
       public       ciecyt_admin    false    197    231    2982         �           2606    52363 Y   adjunto_retroalimentacion adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id FOREIGN KEY (adjunto_retroalimentacion_retroalimentacion_id) REFERENCES public.retroalimentacion(id);
 �   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id;
       public       ciecyt_admin    false    198    234    2988         �           2606    52368 W   cronograma_ciecyt_fases cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id FOREIGN KEY (cronograma_ciecyt_fases_cronograma_ciecyt_id) REFERENCES public.cronograma_ciecyt(id);
 �   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id;
       public       ciecyt_admin    false    203    202    2919         �           2606    52373 $   jhi_user_authority fk_authority_name    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);
 N   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_authority_name;
       public       ciecyt_admin    false    222    218    2949         �           2606    52378 ;   categorizacion fk_categorizacion_categorizacion_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT fk_categorizacion_categorizacion_proyecto_id FOREIGN KEY (categorizacion_proyecto_id) REFERENCES public.proyecto(id);
 e   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT fk_categorizacion_categorizacion_proyecto_id;
       public       ciecyt_admin    false    199    230    2980         �           2606    52383 F   ciclo_propedeutico fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id FOREIGN KEY (ciclo_propedeutico_acuerdo_id) REFERENCES public.acuerdo(id);
 p   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id;
       public       ciecyt_admin    false    200    196    2907         �           2606    52388 5   ciclo_propedeutico fk_ciclo_propedeutico_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_modalidad_id FOREIGN KEY (modalidad_id) REFERENCES public.modalidad(id);
 _   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_modalidad_id;
       public       ciecyt_admin    false    200    225    2970         �           2606    52393 E   cronograma_ciecyt fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id FOREIGN KEY (cronograma_ciecyt_modalidad_id) REFERENCES public.modalidad(id);
 o   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id;
       public       ciecyt_admin    false    202    225    2970         �           2606    52398 S   cronograma_ciecyt_fases fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id FOREIGN KEY (cronograma_ciecyt_fases_fases_id) REFERENCES public.fases(id);
 }   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id;
       public       ciecyt_admin    false    203    211    2935         �           2606    52403 /   cronograma fk_cronograma_cronograma_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT fk_cronograma_cronograma_proyecto_id FOREIGN KEY (cronograma_proyecto_id) REFERENCES public.proyecto(id);
 Y   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT fk_cronograma_cronograma_proyecto_id;
       public       ciecyt_admin    false    201    230    2980         �           2606    52408 (   elemento fk_elemento_elemento_formato_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_formato_id FOREIGN KEY (elemento_formato_id) REFERENCES public.formato(id);
 R   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_formato_id;
       public       ciecyt_admin    false    206    213    2939         �           2606    52413 *   elemento fk_elemento_elemento_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_modalidad_id FOREIGN KEY (elemento_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_modalidad_id;
       public       ciecyt_admin    false    206    225    2970         �           2606    52418 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_elemento_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id FOREIGN KEY (elemento_proyecto_elemento_id) REFERENCES public.elemento(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id;
       public       ciecyt_admin    false    207    206    2925         �           2606    52423 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id FOREIGN KEY (elemento_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id;
       public       ciecyt_admin    false    207    230    2980         �           2606    52428 L   entidad_financiadora fk_entidad_financiadora_entidad_financiadora_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id FOREIGN KEY (entidad_financiadora_entidad_id) REFERENCES public.entidad(id);
 v   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id;
       public       ciecyt_admin    false    209    208    2929         �           2606    52433 8   jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);
 b   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT fk_evt_pers_audit_evt_data;
       public       ciecyt_admin    false    2952    219    220         �           2606    52438 !   fases fk_fases_fases_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fk_fases_fases_modalidad_id FOREIGN KEY (fases_modalidad_id) REFERENCES public.modalidad(id);
 K   ALTER TABLE ONLY public.fases DROP CONSTRAINT fk_fases_fases_modalidad_id;
       public       ciecyt_admin    false    2970    225    211         �           2606    52443 4   ficha_tecnica fk_ficha_tecnica_ficha_tecnica_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id FOREIGN KEY (ficha_tecnica_user_id) REFERENCES public.jhi_user(id);
 ^   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id;
       public       ciecyt_admin    false    212    2957    221         �           2606    52448 F   impactos_esperados fk_impactos_esperados_impactos_esperado_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id FOREIGN KEY (impactos_esperado_proyecto_id) REFERENCES public.proyecto(id);
 p   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id;
       public       ciecyt_admin    false    2980    230    215         �           2606    52453 J   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id FOREIGN KEY (integrante_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id;
       public       ciecyt_admin    false    217    2980    230         �           2606    52458 Q   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id FOREIGN KEY (integrante_proyecto_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 {   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id;
       public       ciecyt_admin    false    2992    236    217         �           2606    52463 F   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id FOREIGN KEY (integrante_proyecto_user_id) REFERENCES public.jhi_user(id);
 p   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id;
       public       ciecyt_admin    false    2957    221    217         �           2606    69002 J   linea_investigacion fk_linea_investigacion_linea_investigacion_programa_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_investigacion_programa_id FOREIGN KEY (linea_investigacion_programa_id) REFERENCES public.programa(id) NOT VALID;
 t   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_investigacion_programa_id;
       public       ciecyt_admin    false    245    3004    223         �           2606    52473 9   linea_investigacion fk_linea_investigacion_linea_padre_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_padre_id FOREIGN KEY (linea_padre_id) REFERENCES public.linea_investigacion(id);
 c   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_padre_id;
       public       ciecyt_admin    false    2966    223    223         �           2606    52478    menu fk_menu_menu_padre_id    FK CONSTRAINT     ~   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fk_menu_menu_padre_id FOREIGN KEY (menu_padre_id) REFERENCES public.menu(id);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT fk_menu_menu_padre_id;
       public       ciecyt_admin    false    2968    224    224         �           2606    52483 +   modalidad fk_modalidad_modalidad_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT fk_modalidad_modalidad_acuerdo_id FOREIGN KEY (modalidad_acuerdo_id) REFERENCES public.acuerdo(id);
 U   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT fk_modalidad_modalidad_acuerdo_id;
       public       ciecyt_admin    false    2907    196    225         �           2606    52488 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public       ciecyt_admin    false    2970    225    226         �           2606    52493 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public       ciecyt_admin    false    236    2992    226         �           2606    52498 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public       ciecyt_admin    false    2998    226    240         �           2606    52503 C   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id FOREIGN KEY (presupuesto_valor_entidad_id) REFERENCES public.entidad(id);
 m   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id;
       public       ciecyt_admin    false    227    208    2929         �           2606    52508 D   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id FOREIGN KEY (presupuesto_valor_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id;
       public       ciecyt_admin    false    230    227    2980         �           2606    52513 A   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_rubro_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id FOREIGN KEY (presupuesto_valor_rubro_id) REFERENCES public.rubro(id);
 k   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id;
       public       ciecyt_admin    false    227    237    2994         �           2606    52518 D   producto_proyecto fk_producto_proyecto_producto_proyecto_producto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id FOREIGN KEY (producto_proyecto_producto_id) REFERENCES public.producto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id;
       public       ciecyt_admin    false    229    228    2976         �           2606    52523 D   producto_proyecto fk_producto_proyecto_producto_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id FOREIGN KEY (producto_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id;
       public       ciecyt_admin    false    229    230    2980         �           2606    52528     proyecto fk_proyecto_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);
 J   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_facultad_id;
       public       ciecyt_admin    false    230    210    2933         �           2606    52533 5   proyecto_fase fk_proyecto_fase_proyecto_fase_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id FOREIGN KEY (proyecto_fase_fases_id) REFERENCES public.fases(id);
 _   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id;
       public       ciecyt_admin    false    231    211    2935         �           2606    52538 8   proyecto_fase fk_proyecto_fase_proyecto_fase_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id FOREIGN KEY (proyecto_fase_proyecto_id) REFERENCES public.proyecto(id);
 b   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id;
       public       ciecyt_admin    false    231    230    2980         �           2606    52543 0   proyecto fk_proyecto_proyecto_grupo_semillero_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);
 Z   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id;
       public       ciecyt_admin    false    230    214    2941         �           2606    52548 4   proyecto fk_proyecto_proyecto_linea_investigacion_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);
 ^   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id;
       public       ciecyt_admin    false    230    223    2966         �           2606    52553 *   proyecto fk_proyecto_proyecto_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_modalidad_id;
       public       ciecyt_admin    false    230    225    2970         �           2606    52558 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id FOREIGN KEY (proyecto_respuestas_pregunta_id) REFERENCES public.pregunta(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id;
       public       ciecyt_admin    false    232    226    2972         �           2606    52563 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id FOREIGN KEY (proyecto_respuestas_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id;
       public       ciecyt_admin    false    232    230    2980         �           2606    52568 M   resultados_esperados fk_resultados_esperados_resultados_esperados_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id FOREIGN KEY (resultados_esperados_proyecto_id) REFERENCES public.proyecto(id);
 w   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id;
       public       ciecyt_admin    false    233    230    2980         �           2606    52573 I   retroalimentacion fk_retroalimentacion_retroalimentacion_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id FOREIGN KEY (retroalimentacion_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 s   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id;
       public       ciecyt_admin    false    234    231    2982         �           2606    52578 @   retroalimentacion fk_retroalimentacion_retroalimentacion_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id FOREIGN KEY (retroalimentacion_user_id) REFERENCES public.jhi_user(id);
 j   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id;
       public       ciecyt_admin    false    234    221    2957         �           2606    52583 %   rol_menu fk_rol_menu_rol_menu_menu_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT fk_rol_menu_rol_menu_menu_id FOREIGN KEY (rol_menu_menu_id) REFERENCES public.menu(id);
 O   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT fk_rol_menu_rol_menu_menu_id;
       public       ciecyt_admin    false    235    2968    224         �           2606    52588 ?   roles_modalidad fk_roles_modalidad_roles_modalidad_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id FOREIGN KEY (roles_modalidad_modalidad_id) REFERENCES public.modalidad(id);
 i   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id;
       public       ciecyt_admin    false    236    2970    225         �           2606    52593 7   solicitud fk_solicitud_solicitud_integrante_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id FOREIGN KEY (solicitud_integrante_proyecto_id) REFERENCES public.integrante_proyecto(id);
 a   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id;
       public       ciecyt_admin    false    2947    217    239         �           2606    52598    jhi_user_authority fk_user_id    FK CONSTRAINT        ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);
 G   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_user_id;
       public       ciecyt_admin    false    222    2957    221                                                                                                                                                                                                                                                                                                3175.dat                                                                                            0000600 0004000 0002000 00000000062 13767147462 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1501	Prueba Acuerdo ADR	1	1123123	2020-03-24
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                              3176.dat                                                                                            0000600 0004000 0002000 00000002125 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	withdrawal	2020-01-24	2020-01-24	56343	disintermediate Jordan Savings Account	deploy Advanced	2020-01-24	2020-01-24	\N
2	Chicken payment	2020-01-23	2020-01-24	50044	Switzerland Operations	Unions Jewelery leading-edge	2020-01-24	2020-01-24	\N
3	Moldova Operative	2020-01-24	2020-01-24	39436	Slovenia synthesize capacitor	Sports violet	2020-01-24	2020-01-23	\N
4	Unbranded Frozen Bike invoice	2020-01-24	2020-01-24	19715	Inlet yellow Shoes	Open-source multi-tasking	2020-01-24	2020-01-23	\N
5	Maldives Kansas Generic	2020-01-24	2020-01-24	39990	Administrator	Granite Computer Dong	2020-01-24	2020-01-23	\N
6	Fish attitude-oriented	2020-01-23	2020-01-24	18522	Principal	Avon neural	2020-01-24	2020-01-24	\N
7	New Caledonia	2020-01-24	2020-01-23	258	invoice Cambridgeshire	Secured olive	2020-01-24	2020-01-24	\N
8	next-generation Human	2020-01-23	2020-01-24	55663	Seamless	object-oriented	2020-01-23	2020-01-23	\N
9	New Leu primary	2020-01-23	2020-01-24	14970	interfaces Mouse	bus	2020-01-23	2020-01-24	\N
10	Bermuda	2020-01-24	2020-01-24	31227	Phased sky blue	Exclusive benchmark	2020-01-24	2020-01-24	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                           3177.dat                                                                                            0000600 0004000 0002000 00000002142 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Plastic	2020-01-24	2020-01-24	34167	evolve plug-and-play	Lithuania	2020-01-24	2020-01-23	\N
2	heuristic Communications	2020-01-23	2020-01-24	16176	online Wyoming Texas	tan Markets	2020-01-24	2020-01-24	\N
3	Investment Account CFA Franc BEAC Syrian Arab Republic	2020-01-24	2020-01-23	14127	orange bypassing Graphic Interface	Keyboard	2020-01-24	2020-01-24	\N
4	Metrics North Carolina	2020-01-23	2020-01-23	57516	Throughway	Investor	2020-01-24	2020-01-24	\N
5	Legacy hacking	2020-01-24	2020-01-24	95279	hacking withdrawal	Sleek Steel Hat	2020-01-24	2020-01-24	\N
6	Awesome parse Cotton	2020-01-24	2020-01-24	93644	ADP attitude-oriented	deposit red Home Loan Account	2020-01-24	2020-01-24	\N
7	streamline	2020-01-24	2020-01-23	19178	green Cambridgeshire	Generic Metal Gloves Administrator Devolved	2020-01-24	2020-01-24	\N
8	virtual	2020-01-24	2020-01-24	17776	Convertible Marks Home Loan Account invoice	open-source	2020-01-24	2020-01-24	\N
9	Soap	2020-01-24	2020-01-24	84801	panel optical	Ports pink	2020-01-24	2020-01-24	\N
10	Buckinghamshire Fish	2020-01-24	2020-01-23	96382	Inverse	Land	2020-01-24	2020-01-23	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                              3178.dat                                                                                            0000600 0004000 0002000 00000000573 13767147462 0014302 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Legacy Slovenia	deposit Steel	\N
2	Unbranded Savings Account	seize	\N
3	Japan maximize	Response	\N
4	Research	tan TCP invoice	\N
5	New Taiwan Dollar	SCSI	\N
6	Universal Egypt invoice	Wall	\N
7	input Rhode Island Tuna	Gloves	\N
8	parse user-facing	Handmade Wooden Computer Metal	\N
9	Licensed Manager Borders	copying	\N
10	relationships quantify generate	array Delaware	\N
\.


                                                                                                                                     3179.dat                                                                                            0000600 0004000 0002000 00000000041 13767147462 0014271 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1601	Prueba Ciclo	1551	1501
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               3180.dat                                                                                            0000600 0004000 0002000 00000000327 13767147462 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30301	Analisis	\N	2020-11-30	2023-12-13	29954	3
30302	Requerimientos	\N	2020-11-20	2020-11-27	29954	2
31352	Bases de datos	\N	2020-12-24	2020-12-29	30951	3
31351	Requerimientos	\N	2020-12-08	2020-12-22	30951	2
\.


                                                                                                                                                                                                                                                                                                         3181.dat                                                                                            0000600 0004000 0002000 00000001240 13767147462 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	magenta efficient	2020-01-23	2020-01-24	approach Borders Unbranded	\N
2	Small bypassing	2020-01-24	2020-01-24	Frozen	\N
3	magenta Turkish Lira	2020-01-23	2020-01-24	Chicken	\N
4	Web	2020-01-24	2020-01-24	Kids Communications Generic Granite Gloves	\N
5	Accountability Assurance Auto Loan Account	2020-01-23	2020-01-24	indexing Handcrafted	\N
6	Malta Auto Loan Account online	2020-01-24	2020-01-24	Central African Republic	\N
7	markets protocol Savings Account	2020-01-24	2020-01-24	HDD pink	\N
8	Hill override PNG	2020-01-24	2020-01-24	Avon	\N
9	Clothing deposit	2020-01-24	2020-01-23	Analyst deposit	\N
10	whiteboard	2020-01-24	2020-01-24	Electronics Switchable	\N
\.


                                                                                                                                                                                                                                                                                                                                                                3182.dat                                                                                            0000600 0004000 0002000 00000000766 13767147462 0014301 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	2020-01-24	2020-01-24	Assurance mindshare navigating	\N	\N
2	2020-01-24	2020-01-24	drive Analyst Awesome Fresh Hat	\N	\N
3	2020-01-24	2020-01-24	Clothing target	\N	\N
4	2020-01-24	2020-01-23	Forward systems	\N	\N
5	2020-01-24	2020-01-23	uniform sticky	\N	\N
6	2020-01-24	2020-01-24	invoice program	\N	\N
7	2020-01-24	2020-01-23	iterate Savings Account deposit	\N	\N
8	2020-01-24	2020-01-23	Shirt SDD	\N	\N
9	2020-01-24	2020-01-24	Refined	\N	\N
10	2020-01-24	2020-01-24	Borders open-source	\N	\N
\.


          3183.dat                                                                                            0000600 0004000 0002000 00000101550 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-03-21 20:48:55.311111	1	EXECUTED	8:b8c27d9dc8db18b5de87cdb8c38a416b	createSequence sequenceName=sequence_generator		\N	3.6.3	\N	\N	4841734745
20200124174449-1-data	jhipster	config/liquibase/changelog/20200124174449_added_entity_GrupoSemillero.xml	2020-03-21 20:48:56.888749	11	EXECUTED	\N	loadData tableName=grupo_semillero		\N	3.6.3	faker	\N	4841734745
20200124174450-1	jhipster	config/liquibase/changelog/20200124174450_added_entity_Facultad.xml	2020-03-21 20:48:56.912779	12	EXECUTED	\N	createTable tableName=facultad		\N	3.6.3	\N	\N	4841734745
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-03-21 20:48:56.674615	2	EXECUTED	8:477f075f156771f4b4a08c934b4457d8	createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...		\N	3.6.3	\N	\N	4841734745
20200124174452-1-relations	jhipster	config/liquibase/changelog/20200124174452_added_entity_Acuerdo.xml	2020-03-21 20:48:57.05893	19	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174453-1	jhipster	config/liquibase/changelog/20200124174453_added_entity_CicloPropedeutico.xml	2020-03-21 20:48:57.120256	21	EXECUTED	\N	createTable tableName=ciclo_propedeutico		\N	3.6.3	\N	\N	4841734745
20200124174454-1-relations	jhipster	config/liquibase/changelog/20200124174454_added_entity_ResultadosEsperados.xml	2020-03-21 20:48:57.229916	25	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174455-1-data	jhipster	config/liquibase/changelog/20200124174455_added_entity_Producto.xml	2020-03-21 20:48:57.325289	29	EXECUTED	\N	loadData tableName=producto		\N	3.6.3	faker	\N	4841734745
20200124174456-1-relations	jhipster	config/liquibase/changelog/20200124174456_added_entity_ProductoProyecto.xml	2020-03-21 20:48:57.35923	31	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174458-1-data	jhipster	config/liquibase/changelog/20200124174458_added_entity_Cronograma.xml	2020-03-21 20:48:57.524472	38	EXECUTED	\N	loadData tableName=cronograma		\N	3.6.3	faker	\N	4841734745
20200124174501-1-data	jhipster	config/liquibase/changelog/20200124174501_added_entity_Entidad.xml	2020-03-21 20:48:57.721008	47	EXECUTED	\N	loadData tableName=entidad		\N	3.6.3	faker	\N	4841734745
20200124174504-1-relations	jhipster	config/liquibase/changelog/20200124174504_added_entity_ElementoProyecto.xml	2020-03-21 20:48:57.903638	55	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174506-1-data	jhipster	config/liquibase/changelog/20200124174506_added_entity_TipoPregunta.xml	2020-03-21 20:48:58.07047	62	EXECUTED	\N	loadData tableName=tipo_pregunta		\N	3.6.3	faker	\N	4841734745
20200124174511-1	jhipster	config/liquibase/changelog/20200124174511_added_entity_ProyectoFase.xml	2020-03-21 20:48:58.404348	75	EXECUTED	\N	createTable tableName=proyecto_fase		\N	3.6.3	\N	\N	4841734745
20200124174512-1-data	jhipster	config/liquibase/changelog/20200124174512_added_entity_CronogramaCiecyt.xml	2020-03-21 20:48:58.532893	80	EXECUTED	\N	loadData tableName=cronograma_ciecyt		\N	3.6.3	faker	\N	4841734745
20200124174515-1-data	jhipster	config/liquibase/changelog/20200124174515_added_entity_Solicitud.xml	2020-03-21 20:48:58.747058	89	EXECUTED	\N	loadData tableName=solicitud		\N	3.6.3	faker	\N	4841734745
20200124174517-1-data	jhipster	config/liquibase/changelog/20200124174517_added_entity_Retroalimentacion.xml	2020-03-21 20:48:58.888463	95	EXECUTED	\N	loadData tableName=retroalimentacion		\N	3.6.3	faker	\N	4841734745
20200124174518-1-data	jhipster	config/liquibase/changelog/20200124174518_added_entity_AdjuntoRetroalimentacion.xml	2020-03-21 20:48:58.959924	98	EXECUTED	\N	loadData tableName=adjunto_retroalimentacion		\N	3.6.3	faker	\N	4841734745
20200124174521-1	jhipster	config/liquibase/changelog/20200124174521_added_entity_Usuario.xml	2020-03-21 20:48:59.139361	105	EXECUTED	\N	createTable tableName=usuario		\N	3.6.3	\N	\N	4841734745
20200124174447-2	jhipster	config/liquibase/changelog/20200124174447_added_entity_constraints_Proyecto.xml	2020-03-21 20:48:59.372623	114	EXECUTED	\N	addForeignKeyConstraint baseTableName=proyecto, constraintName=fk_proyecto_proyecto_linea_investigacion_id, referencedTableName=linea_investigacion; addForeignKeyConstraint baseTableName=proyecto, constraintName=fk_proyecto_proyecto_grupo_semiller...		\N	3.6.3	\N	\N	4841734745
20200124174511-2	jhipster	config/liquibase/changelog/20200124174511_added_entity_constraints_ProyectoFase.xml	2020-03-21 20:48:59.82444	130	EXECUTED	\N	addForeignKeyConstraint baseTableName=proyecto_fase, constraintName=fk_proyecto_fase_proyecto_fase_fases_id, referencedTableName=fases; addForeignKeyConstraint baseTableName=proyecto_fase, constraintName=fk_proyecto_fase_proyecto_fase_proyecto_id,...		\N	3.6.3	\N	\N	4841734745
1584914994805-4	ITPLAP-06 (generated)	config/liquibase/changelog/20200322220730_modify_entity_Menu.xml	2020-03-22 18:06:46.964856	142	EXECUTED	\N	addColumn tableName=menu		\N	3.6.3	\N	\N	4918407244
1584914994805-5	ITPLAP-06 (generated)	config/liquibase/changelog/20200322220730_modify_entity_Menu.xml	2020-03-22 18:06:47.590716	143	EXECUTED	\N	addColumn tableName=menu		\N	3.6.3	\N	\N	4918407244
20200124174448-1	jhipster	config/liquibase/changelog/20200124174448_added_entity_LineaInvestigacion.xml	2020-03-21 20:48:56.784404	6	EXECUTED	\N	createTable tableName=linea_investigacion		\N	3.6.3	\N	\N	4841734745
20200124174448-1-data	jhipster	config/liquibase/changelog/20200124174448_added_entity_LineaInvestigacion.xml	2020-03-21 20:48:56.822748	8	EXECUTED	\N	loadData tableName=linea_investigacion		\N	3.6.3	faker	\N	4841734745
20200124174449-1	jhipster	config/liquibase/changelog/20200124174449_added_entity_GrupoSemillero.xml	2020-03-21 20:48:56.844754	9	EXECUTED	\N	createTable tableName=grupo_semillero		\N	3.6.3	\N	\N	4841734745
20200124174449-1-relations	jhipster	config/liquibase/changelog/20200124174449_added_entity_GrupoSemillero.xml	2020-03-21 20:48:56.856038	10	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174450-1-relations	jhipster	config/liquibase/changelog/20200124174450_added_entity_Facultad.xml	2020-03-21 20:48:56.923932	13	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174450-1-data	jhipster	config/liquibase/changelog/20200124174450_added_entity_Facultad.xml	2020-03-21 20:48:56.951496	14	EXECUTED	\N	loadData tableName=facultad		\N	3.6.3	faker	\N	4841734745
20200124174451-1	jhipster	config/liquibase/changelog/20200124174451_added_entity_Modalidad.xml	2020-03-21 20:48:56.984781	15	EXECUTED	\N	createTable tableName=modalidad		\N	3.6.3	\N	\N	4841734745
20200124174451-1-relations	jhipster	config/liquibase/changelog/20200124174451_added_entity_Modalidad.xml	2020-03-21 20:48:56.995995	16	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174451-1-data	jhipster	config/liquibase/changelog/20200124174451_added_entity_Modalidad.xml	2020-03-21 20:48:57.0212	17	EXECUTED	\N	loadData tableName=modalidad		\N	3.6.3	faker	\N	4841734745
20200124174452-1	jhipster	config/liquibase/changelog/20200124174452_added_entity_Acuerdo.xml	2020-03-21 20:48:57.048751	18	EXECUTED	\N	createTable tableName=acuerdo		\N	3.6.3	\N	\N	4841734745
20200124174452-1-data	jhipster	config/liquibase/changelog/20200124174452_added_entity_Acuerdo.xml	2020-03-21 20:48:57.094256	20	EXECUTED	\N	loadData tableName=acuerdo		\N	3.6.3	faker	\N	4841734745
20200124174457-1-relations	jhipster	config/liquibase/changelog/20200124174457_added_entity_ImpactosEsperados.xml	2020-03-21 20:48:57.425852	34	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174458-1-relations	jhipster	config/liquibase/changelog/20200124174458_added_entity_Cronograma.xml	2020-03-21 20:48:57.494764	37	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174459-1	jhipster	config/liquibase/changelog/20200124174459_added_entity_Rubro.xml	2020-03-21 20:48:57.549763	39	EXECUTED	\N	createTable tableName=rubro		\N	3.6.3	\N	\N	4841734745
20200124174459-1-relations	jhipster	config/liquibase/changelog/20200124174459_added_entity_Rubro.xml	2020-03-21 20:48:57.560077	40	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174459-1-data	jhipster	config/liquibase/changelog/20200124174459_added_entity_Rubro.xml	2020-03-21 20:48:57.591211	41	EXECUTED	\N	loadData tableName=rubro		\N	3.6.3	faker	\N	4841734745
20200124174503-1-relations	jhipster	config/liquibase/changelog/20200124174503_added_entity_Elemento.xml	2020-03-21 20:48:57.837447	52	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174505-1	jhipster	config/liquibase/changelog/20200124174505_added_entity_Formato.xml	2020-03-21 20:48:57.953453	57	EXECUTED	\N	createTable tableName=formato		\N	3.6.3	\N	\N	4841734745
20200124174507-1-relations	jhipster	config/liquibase/changelog/20200124174507_added_entity_Pregunta.xml	2020-03-21 20:48:58.117283	64	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174511-1-data	jhipster	config/liquibase/changelog/20200124174511_added_entity_ProyectoFase.xml	2020-03-21 20:48:58.461384	77	EXECUTED	\N	loadData tableName=proyecto_fase		\N	3.6.3	faker	\N	4841734745
20200124174513-1-data	jhipster	config/liquibase/changelog/20200124174513_added_entity_CronogramaCiecytFases.xml	2020-03-21 20:48:58.606426	83	EXECUTED	\N	loadData tableName=cronograma_ciecyt_fases		\N	3.6.3	faker	\N	4841734745
20200124174516-1	jhipster	config/liquibase/changelog/20200124174516_added_entity_AdjuntoProyectoFase.xml	2020-03-21 20:48:58.772677	90	EXECUTED	\N	createTable tableName=adjunto_proyecto_fase		\N	3.6.3	\N	\N	4841734745
20200124174520-1-data	jhipster	config/liquibase/changelog/20200124174520_added_entity_Categorizacion.xml	2020-03-21 20:48:59.108752	104	EXECUTED	\N	loadData tableName=categorizacion		\N	3.6.3	faker	\N	4841734745
20200319194601-1-data	jhipster	config/liquibase/changelog/20200319194601_added_entity_RolMenu.xml	2020-03-21 20:48:59.331204	113	EXECUTED	\N	loadData tableName=rol_menu		\N	3.6.3	faker	\N	4841734745
20200124174448-2	jhipster	config/liquibase/changelog/20200124174448_added_entity_constraints_LineaInvestigacion.xml	2020-03-21 20:48:59.405622	115	EXECUTED	\N	addForeignKeyConstraint baseTableName=linea_investigacion, constraintName=fk_linea_investigacion_linea_padre_id, referencedTableName=linea_investigacion; addForeignKeyConstraint baseTableName=linea_investigacion, constraintName=fk_linea_investigac...		\N	3.6.3	\N	\N	4841734745
20200124174453-1-relations	jhipster	config/liquibase/changelog/20200124174453_added_entity_CicloPropedeutico.xml	2020-03-21 20:48:57.134031	22	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174453-1-data	jhipster	config/liquibase/changelog/20200124174453_added_entity_CicloPropedeutico.xml	2020-03-21 20:48:57.187081	23	EXECUTED	\N	loadData tableName=ciclo_propedeutico		\N	3.6.3	faker	\N	4841734745
20200124174454-1	jhipster	config/liquibase/changelog/20200124174454_added_entity_ResultadosEsperados.xml	2020-03-21 20:48:57.216346	24	EXECUTED	\N	createTable tableName=resultados_esperados		\N	3.6.3	\N	\N	4841734745
20200124174454-1-data	jhipster	config/liquibase/changelog/20200124174454_added_entity_ResultadosEsperados.xml	2020-03-21 20:48:57.260756	26	EXECUTED	\N	loadData tableName=resultados_esperados		\N	3.6.3	faker	\N	4841734745
20200124174455-1	jhipster	config/liquibase/changelog/20200124174455_added_entity_Producto.xml	2020-03-21 20:48:57.28716	27	EXECUTED	\N	createTable tableName=producto		\N	3.6.3	\N	\N	4841734745
20200124174455-1-relations	jhipster	config/liquibase/changelog/20200124174455_added_entity_Producto.xml	2020-03-21 20:48:57.299266	28	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174456-1	jhipster	config/liquibase/changelog/20200124174456_added_entity_ProductoProyecto.xml	2020-03-21 20:48:57.3483	30	EXECUTED	\N	createTable tableName=producto_proyecto		\N	3.6.3	\N	\N	4841734745
20200124174456-1-data	jhipster	config/liquibase/changelog/20200124174456_added_entity_ProductoProyecto.xml	2020-03-21 20:48:57.394744	32	EXECUTED	\N	loadData tableName=producto_proyecto		\N	3.6.3	faker	\N	4841734745
20200124174457-1	jhipster	config/liquibase/changelog/20200124174457_added_entity_ImpactosEsperados.xml	2020-03-21 20:48:57.416047	33	EXECUTED	\N	createTable tableName=impactos_esperados		\N	3.6.3	\N	\N	4841734745
20200124174457-1-data	jhipster	config/liquibase/changelog/20200124174457_added_entity_ImpactosEsperados.xml	2020-03-21 20:48:57.456285	35	EXECUTED	\N	loadData tableName=impactos_esperados		\N	3.6.3	faker	\N	4841734745
20200124174458-1	jhipster	config/liquibase/changelog/20200124174458_added_entity_Cronograma.xml	2020-03-21 20:48:57.484193	36	EXECUTED	\N	createTable tableName=cronograma		\N	3.6.3	\N	\N	4841734745
20200124174500-1-relations	jhipster	config/liquibase/changelog/20200124174500_added_entity_PresupuestoValor.xml	2020-03-21 20:48:57.628986	43	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174500-1-data	jhipster	config/liquibase/changelog/20200124174500_added_entity_PresupuestoValor.xml	2020-03-21 20:48:57.661538	44	EXECUTED	\N	loadData tableName=presupuesto_valor		\N	3.6.3	faker	\N	4841734745
20200124174501-1	jhipster	config/liquibase/changelog/20200124174501_added_entity_Entidad.xml	2020-03-21 20:48:57.684797	45	EXECUTED	\N	createTable tableName=entidad		\N	3.6.3	\N	\N	4841734745
20200124174501-1-relations	jhipster	config/liquibase/changelog/20200124174501_added_entity_Entidad.xml	2020-03-21 20:48:57.695505	46	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174502-1	jhipster	config/liquibase/changelog/20200124174502_added_entity_EntidadFinanciadora.xml	2020-03-21 20:48:57.74324	48	EXECUTED	\N	createTable tableName=entidad_financiadora		\N	3.6.3	\N	\N	4841734745
20200124174502-1-relations	jhipster	config/liquibase/changelog/20200124174502_added_entity_EntidadFinanciadora.xml	2020-03-21 20:48:57.75426	49	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174502-1-data	jhipster	config/liquibase/changelog/20200124174502_added_entity_EntidadFinanciadora.xml	2020-03-21 20:48:57.78153	50	EXECUTED	\N	loadData tableName=entidad_financiadora		\N	3.6.3	faker	\N	4841734745
20200124174503-1	jhipster	config/liquibase/changelog/20200124174503_added_entity_Elemento.xml	2020-03-21 20:48:57.827533	51	EXECUTED	\N	createTable tableName=elemento		\N	3.6.3	\N	\N	4841734745
20200124174503-1-data	jhipster	config/liquibase/changelog/20200124174503_added_entity_Elemento.xml	2020-03-21 20:48:57.867303	53	EXECUTED	\N	loadData tableName=elemento		\N	3.6.3	faker	\N	4841734745
20200124174504-1	jhipster	config/liquibase/changelog/20200124174504_added_entity_ElementoProyecto.xml	2020-03-21 20:48:57.894275	54	EXECUTED	\N	createTable tableName=elemento_proyecto		\N	3.6.3	\N	\N	4841734745
20200124174504-1-data	jhipster	config/liquibase/changelog/20200124174504_added_entity_ElementoProyecto.xml	2020-03-21 20:48:57.929917	56	EXECUTED	\N	loadData tableName=elemento_proyecto		\N	3.6.3	faker	\N	4841734745
20200124174505-1-relations	jhipster	config/liquibase/changelog/20200124174505_added_entity_Formato.xml	2020-03-21 20:48:57.974856	58	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174505-1-data	jhipster	config/liquibase/changelog/20200124174505_added_entity_Formato.xml	2020-03-21 20:48:58.005911	59	EXECUTED	\N	loadData tableName=formato		\N	3.6.3	faker	\N	4841734745
20200124174506-1	jhipster	config/liquibase/changelog/20200124174506_added_entity_TipoPregunta.xml	2020-03-21 20:48:58.031272	60	EXECUTED	\N	createTable tableName=tipo_pregunta		\N	3.6.3	\N	\N	4841734745
20200124174506-1-relations	jhipster	config/liquibase/changelog/20200124174506_added_entity_TipoPregunta.xml	2020-03-21 20:48:58.045182	61	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174507-1	jhipster	config/liquibase/changelog/20200124174507_added_entity_Pregunta.xml	2020-03-21 20:48:58.104857	63	EXECUTED	\N	createTable tableName=pregunta		\N	3.6.3	\N	\N	4841734745
20200124174507-1-data	jhipster	config/liquibase/changelog/20200124174507_added_entity_Pregunta.xml	2020-03-21 20:48:58.145054	65	EXECUTED	\N	loadData tableName=pregunta		\N	3.6.3	faker	\N	4841734745
20200124174508-1	jhipster	config/liquibase/changelog/20200124174508_added_entity_ProyectoRespuestas.xml	2020-03-21 20:48:58.194191	66	EXECUTED	\N	createTable tableName=proyecto_respuestas		\N	3.6.3	\N	\N	4841734745
20200124174508-1-relations	jhipster	config/liquibase/changelog/20200124174508_added_entity_ProyectoRespuestas.xml	2020-03-21 20:48:58.215038	67	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174508-1-data	jhipster	config/liquibase/changelog/20200124174508_added_entity_ProyectoRespuestas.xml	2020-03-21 20:48:58.246038	68	EXECUTED	\N	loadData tableName=proyecto_respuestas		\N	3.6.3	faker	\N	4841734745
20200124174509-1	jhipster	config/liquibase/changelog/20200124174509_added_entity_RolesModalidad.xml	2020-03-21 20:48:58.269255	69	EXECUTED	\N	createTable tableName=roles_modalidad		\N	3.6.3	\N	\N	4841734745
20200124174509-1-relations	jhipster	config/liquibase/changelog/20200124174509_added_entity_RolesModalidad.xml	2020-03-21 20:48:58.282423	70	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174509-1-data	jhipster	config/liquibase/changelog/20200124174509_added_entity_RolesModalidad.xml	2020-03-21 20:48:58.314555	71	EXECUTED	\N	loadData tableName=roles_modalidad		\N	3.6.3	faker	\N	4841734745
20200124174510-1	jhipster	config/liquibase/changelog/20200124174510_added_entity_Fases.xml	2020-03-21 20:48:58.336241	72	EXECUTED	\N	createTable tableName=fases		\N	3.6.3	\N	\N	4841734745
20200124174510-1-relations	jhipster	config/liquibase/changelog/20200124174510_added_entity_Fases.xml	2020-03-21 20:48:58.347193	73	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174510-1-data	jhipster	config/liquibase/changelog/20200124174510_added_entity_Fases.xml	2020-03-21 20:48:58.376851	74	EXECUTED	\N	loadData tableName=fases		\N	3.6.3	faker	\N	4841734745
20200124174511-1-relations	jhipster	config/liquibase/changelog/20200124174511_added_entity_ProyectoFase.xml	2020-03-21 20:48:58.414245	76	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174512-1-relations	jhipster	config/liquibase/changelog/20200124174512_added_entity_CronogramaCiecyt.xml	2020-03-21 20:48:58.49298	79	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174513-1	jhipster	config/liquibase/changelog/20200124174513_added_entity_CronogramaCiecytFases.xml	2020-03-21 20:48:58.559872	81	EXECUTED	\N	createTable tableName=cronograma_ciecyt_fases		\N	3.6.3	\N	\N	4841734745
20200124174513-1-relations	jhipster	config/liquibase/changelog/20200124174513_added_entity_CronogramaCiecytFases.xml	2020-03-21 20:48:58.575696	82	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174514-1	jhipster	config/liquibase/changelog/20200124174514_added_entity_IntegranteProyecto.xml	2020-03-21 20:48:58.630795	84	EXECUTED	\N	createTable tableName=integrante_proyecto		\N	3.6.3	\N	\N	4841734745
20200124174514-1-relations	jhipster	config/liquibase/changelog/20200124174514_added_entity_IntegranteProyecto.xml	2020-03-21 20:48:58.6445	85	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174514-1-data	jhipster	config/liquibase/changelog/20200124174514_added_entity_IntegranteProyecto.xml	2020-03-21 20:48:58.67096	86	EXECUTED	\N	loadData tableName=integrante_proyecto		\N	3.6.3	faker	\N	4841734745
20200124174515-1	jhipster	config/liquibase/changelog/20200124174515_added_entity_Solicitud.xml	2020-03-21 20:48:58.700164	87	EXECUTED	\N	createTable tableName=solicitud		\N	3.6.3	\N	\N	4841734745
20200124174515-1-relations	jhipster	config/liquibase/changelog/20200124174515_added_entity_Solicitud.xml	2020-03-21 20:48:58.718989	88	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174516-1-relations	jhipster	config/liquibase/changelog/20200124174516_added_entity_AdjuntoProyectoFase.xml	2020-03-21 20:48:58.785626	91	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174516-1-data	jhipster	config/liquibase/changelog/20200124174516_added_entity_AdjuntoProyectoFase.xml	2020-03-21 20:48:58.821549	92	EXECUTED	\N	loadData tableName=adjunto_proyecto_fase		\N	3.6.3	faker	\N	4841734745
20200124174517-1	jhipster	config/liquibase/changelog/20200124174517_added_entity_Retroalimentacion.xml	2020-03-21 20:48:58.847702	93	EXECUTED	\N	createTable tableName=retroalimentacion		\N	3.6.3	\N	\N	4841734745
20200124174517-1-relations	jhipster	config/liquibase/changelog/20200124174517_added_entity_Retroalimentacion.xml	2020-03-21 20:48:58.860659	94	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174518-1	jhipster	config/liquibase/changelog/20200124174518_added_entity_AdjuntoRetroalimentacion.xml	2020-03-21 20:48:58.913309	96	EXECUTED	\N	createTable tableName=adjunto_retroalimentacion		\N	3.6.3	\N	\N	4841734745
20200124174518-1-relations	jhipster	config/liquibase/changelog/20200124174518_added_entity_AdjuntoRetroalimentacion.xml	2020-03-21 20:48:58.924427	97	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174519-1	jhipster	config/liquibase/changelog/20200124174519_added_entity_FichaTecnica.xml	2020-03-21 20:48:58.983722	99	EXECUTED	\N	createTable tableName=ficha_tecnica		\N	3.6.3	\N	\N	4841734745
20200124174519-1-relations	jhipster	config/liquibase/changelog/20200124174519_added_entity_FichaTecnica.xml	2020-03-21 20:48:58.996173	100	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174519-1-data	jhipster	config/liquibase/changelog/20200124174519_added_entity_FichaTecnica.xml	2020-03-21 20:48:59.022245	101	EXECUTED	\N	loadData tableName=ficha_tecnica		\N	3.6.3	faker	\N	4841734745
20200124174520-1	jhipster	config/liquibase/changelog/20200124174520_added_entity_Categorizacion.xml	2020-03-21 20:48:59.05727	102	EXECUTED	\N	createTable tableName=categorizacion		\N	3.6.3	\N	\N	4841734745
20200124174520-1-relations	jhipster	config/liquibase/changelog/20200124174520_added_entity_Categorizacion.xml	2020-03-21 20:48:59.068892	103	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174521-1-relations	jhipster	config/liquibase/changelog/20200124174521_added_entity_Usuario.xml	2020-03-21 20:48:59.150359	106	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174521-1-data	jhipster	config/liquibase/changelog/20200124174521_added_entity_Usuario.xml	2020-03-21 20:48:59.18346	107	EXECUTED	\N	loadData tableName=usuario		\N	3.6.3	faker	\N	4841734745
20200319194600-1	jhipster	config/liquibase/changelog/20200319194600_added_entity_Menu.xml	2020-03-21 20:48:59.209903	108	EXECUTED	\N	createTable tableName=menu		\N	3.6.3	\N	\N	4841734745
20200319194600-1-relations	jhipster	config/liquibase/changelog/20200319194600_added_entity_Menu.xml	2020-03-21 20:48:59.228214	109	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200319194600-1-data	jhipster	config/liquibase/changelog/20200319194600_added_entity_Menu.xml	2020-03-21 20:48:59.255059	110	EXECUTED	\N	loadData tableName=menu		\N	3.6.3	faker	\N	4841734745
20200124174500-2	jhipster	config/liquibase/changelog/20200124174500_added_entity_constraints_PresupuestoValor.xml	2020-03-21 20:48:59.583274	122	EXECUTED	\N	addForeignKeyConstraint baseTableName=presupuesto_valor, constraintName=fk_presupuesto_valor_presupuesto_valor_rubro_id, referencedTableName=rubro; addForeignKeyConstraint baseTableName=presupuesto_valor, constraintName=fk_presupuesto_valor_presup...		\N	3.6.3	\N	\N	4841734745
20200124174451-2	jhipster	config/liquibase/changelog/20200124174451_added_entity_constraints_Modalidad.xml	2020-03-21 20:48:59.429178	116	EXECUTED	\N	addForeignKeyConstraint baseTableName=modalidad, constraintName=fk_modalidad_modalidad_acuerdo_id, referencedTableName=acuerdo		\N	3.6.3	\N	\N	4841734745
20200124174502-2	jhipster	config/liquibase/changelog/20200124174502_added_entity_constraints_EntidadFinanciadora.xml	2020-03-21 20:48:59.605506	123	EXECUTED	\N	addForeignKeyConstraint baseTableName=entidad_financiadora, constraintName=fk_entidad_financiadora_entidad_financiadora_entidad_id, referencedTableName=entidad		\N	3.6.3	\N	\N	4841734745
20200124174503-2	jhipster	config/liquibase/changelog/20200124174503_added_entity_constraints_Elemento.xml	2020-03-21 20:48:59.63796	124	EXECUTED	\N	addForeignKeyConstraint baseTableName=elemento, constraintName=fk_elemento_elemento_formato_id, referencedTableName=formato; addForeignKeyConstraint baseTableName=elemento, constraintName=fk_elemento_elemento_modalidad_id, referencedTableName=moda...		\N	3.6.3	\N	\N	4841734745
20200124174504-2	jhipster	config/liquibase/changelog/20200124174504_added_entity_constraints_ElementoProyecto.xml	2020-03-21 20:48:59.663625	125	EXECUTED	\N	addForeignKeyConstraint baseTableName=elemento_proyecto, constraintName=fk_elemento_proyecto_elemento_proyecto_elemento_id, referencedTableName=elemento; addForeignKeyConstraint baseTableName=elemento_proyecto, constraintName=fk_elemento_proyecto_...		\N	3.6.3	\N	\N	4841734745
20200124174507-2	jhipster	config/liquibase/changelog/20200124174507_added_entity_constraints_Pregunta.xml	2020-03-21 20:48:59.695873	126	EXECUTED	\N	addForeignKeyConstraint baseTableName=pregunta, constraintName=fk_pregunta_pregunta_tipo_pregunta_id, referencedTableName=tipo_pregunta; addForeignKeyConstraint baseTableName=pregunta, constraintName=fk_pregunta_pregunta_modalidad_id, referencedTa...		\N	3.6.3	\N	\N	4841734745
20200124174508-2	jhipster	config/liquibase/changelog/20200124174508_added_entity_constraints_ProyectoRespuestas.xml	2020-03-21 20:48:59.743869	127	EXECUTED	\N	addForeignKeyConstraint baseTableName=proyecto_respuestas, constraintName=fk_proyecto_respuestas_proyecto_respuestas_pregunta_id, referencedTableName=pregunta; addForeignKeyConstraint baseTableName=proyecto_respuestas, constraintName=fk_proyecto_r...		\N	3.6.3	\N	\N	4841734745
20200124174509-2	jhipster	config/liquibase/changelog/20200124174509_added_entity_constraints_RolesModalidad.xml	2020-03-21 20:48:59.767058	128	EXECUTED	\N	addForeignKeyConstraint baseTableName=roles_modalidad, constraintName=fk_roles_modalidad_roles_modalidad_modalidad_id, referencedTableName=modalidad		\N	3.6.3	\N	\N	4841734745
20200124174510-2	jhipster	config/liquibase/changelog/20200124174510_added_entity_constraints_Fases.xml	2020-03-21 20:48:59.792998	129	EXECUTED	\N	addForeignKeyConstraint baseTableName=fases, constraintName=fk_fases_fases_modalidad_id, referencedTableName=modalidad		\N	3.6.3	\N	\N	4841734745
20200124174512-2	jhipster	config/liquibase/changelog/20200124174512_added_entity_constraints_CronogramaCiecyt.xml	2020-03-21 20:48:59.848307	131	EXECUTED	\N	addForeignKeyConstraint baseTableName=cronograma_ciecyt, constraintName=fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id, referencedTableName=modalidad		\N	3.6.3	\N	\N	4841734745
20200124174513-2	jhipster	config/liquibase/changelog/20200124174513_added_entity_constraints_CronogramaCiecytFases.xml	2020-03-21 20:48:59.876565	132	EXECUTED	\N	addForeignKeyConstraint baseTableName=cronograma_ciecyt_fases, constraintName=cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id, referencedTableName=cronograma_ciecyt; addForeignKeyConstraint baseTableName=cronograma_ciecyt_fases, co...		\N	3.6.3	\N	\N	4841734745
20200124174514-2	jhipster	config/liquibase/changelog/20200124174514_added_entity_constraints_IntegranteProyecto.xml	2020-03-21 20:48:59.91102	133	EXECUTED	\N	addForeignKeyConstraint baseTableName=integrante_proyecto, constraintName=fk_integrante_proyecto_integrante_proyecto_user_id, referencedTableName=jhi_user; addForeignKeyConstraint baseTableName=integrante_proyecto, constraintName=fk_integrante_pro...		\N	3.6.3	\N	\N	4841734745
20200124174520-2	jhipster	config/liquibase/changelog/20200124174520_added_entity_constraints_Categorizacion.xml	2020-03-21 20:49:00.093364	139	EXECUTED	\N	addForeignKeyConstraint baseTableName=categorizacion, constraintName=fk_categorizacion_categorizacion_proyecto_id, referencedTableName=proyecto		\N	3.6.3	\N	\N	4841734745
20200319194601-2	jhipster	config/liquibase/changelog/20200319194601_added_entity_constraints_RolMenu.xml	2020-03-21 20:49:00.164282	141	EXECUTED	\N	addForeignKeyConstraint baseTableName=rol_menu, constraintName=fk_rol_menu_rol_menu_menu_id, referencedTableName=menu		\N	3.6.3	\N	\N	4841734745
20200319194601-1	jhipster	config/liquibase/changelog/20200319194601_added_entity_RolMenu.xml	2020-03-21 20:48:59.286736	111	EXECUTED	\N	createTable tableName=rol_menu		\N	3.6.3	\N	\N	4841734745
20200319194601-1-relations	jhipster	config/liquibase/changelog/20200319194601_added_entity_RolMenu.xml	2020-03-21 20:48:59.298447	112	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174453-2	jhipster	config/liquibase/changelog/20200124174453_added_entity_constraints_CicloPropedeutico.xml	2020-03-21 20:48:59.456317	117	EXECUTED	\N	addForeignKeyConstraint baseTableName=ciclo_propedeutico, constraintName=fk_ciclo_propedeutico_modalidad_id, referencedTableName=modalidad; addForeignKeyConstraint baseTableName=ciclo_propedeutico, constraintName=fk_ciclo_propedeutico_ciclo_proped...		\N	3.6.3	\N	\N	4841734745
20200124174456-2	jhipster	config/liquibase/changelog/20200124174456_added_entity_constraints_ProductoProyecto.xml	2020-03-21 20:48:59.505016	119	EXECUTED	\N	addForeignKeyConstraint baseTableName=producto_proyecto, constraintName=fk_producto_proyecto_producto_proyecto_producto_id, referencedTableName=producto; addForeignKeyConstraint baseTableName=producto_proyecto, constraintName=fk_producto_proyecto_...		\N	3.6.3	\N	\N	4841734745
20200124174457-2	jhipster	config/liquibase/changelog/20200124174457_added_entity_constraints_ImpactosEsperados.xml	2020-03-21 20:48:59.531593	120	EXECUTED	\N	addForeignKeyConstraint baseTableName=impactos_esperados, constraintName=fk_impactos_esperados_impactos_esperado_proyecto_id, referencedTableName=proyecto		\N	3.6.3	\N	\N	4841734745
20200124174458-2	jhipster	config/liquibase/changelog/20200124174458_added_entity_constraints_Cronograma.xml	2020-03-21 20:48:59.55163	121	EXECUTED	\N	addForeignKeyConstraint baseTableName=cronograma, constraintName=fk_cronograma_cronograma_proyecto_id, referencedTableName=proyecto		\N	3.6.3	\N	\N	4841734745
20200124174447-1	jhipster	config/liquibase/changelog/20200124174447_added_entity_Proyecto.xml	2020-03-21 20:48:56.708546	3	EXECUTED	\N	createTable tableName=proyecto		\N	3.6.3	\N	\N	4841734745
20200124174447-1-relations	jhipster	config/liquibase/changelog/20200124174447_added_entity_Proyecto.xml	2020-03-21 20:48:56.720646	4	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174447-1-data	jhipster	config/liquibase/changelog/20200124174447_added_entity_Proyecto.xml	2020-03-21 20:48:56.757265	5	EXECUTED	\N	loadData tableName=proyecto		\N	3.6.3	faker	\N	4841734745
20200124174448-1-relations	jhipster	config/liquibase/changelog/20200124174448_added_entity_LineaInvestigacion.xml	2020-03-21 20:48:56.796405	7	EXECUTED	\N	empty		\N	3.6.3	\N	\N	4841734745
20200124174500-1	jhipster	config/liquibase/changelog/20200124174500_added_entity_PresupuestoValor.xml	2020-03-21 20:48:57.61738	42	EXECUTED	\N	createTable tableName=presupuesto_valor		\N	3.6.3	\N	\N	4841734745
20200124174512-1	jhipster	config/liquibase/changelog/20200124174512_added_entity_CronogramaCiecyt.xml	2020-03-21 20:48:58.484718	78	EXECUTED	\N	createTable tableName=cronograma_ciecyt		\N	3.6.3	\N	\N	4841734745
20200124174454-2	jhipster	config/liquibase/changelog/20200124174454_added_entity_constraints_ResultadosEsperados.xml	2020-03-21 20:48:59.476529	118	EXECUTED	\N	addForeignKeyConstraint baseTableName=resultados_esperados, constraintName=fk_resultados_esperados_resultados_esperados_proyecto_id, referencedTableName=proyecto		\N	3.6.3	\N	\N	4841734745
20200124174515-2	jhipster	config/liquibase/changelog/20200124174515_added_entity_constraints_Solicitud.xml	2020-03-21 20:48:59.931093	134	EXECUTED	\N	addForeignKeyConstraint baseTableName=solicitud, constraintName=fk_solicitud_solicitud_integrante_proyecto_id, referencedTableName=integrante_proyecto		\N	3.6.3	\N	\N	4841734745
20200124174516-2	jhipster	config/liquibase/changelog/20200124174516_added_entity_constraints_AdjuntoProyectoFase.xml	2020-03-21 20:48:59.960031	135	EXECUTED	\N	addForeignKeyConstraint baseTableName=adjunto_proyecto_fase, constraintName=adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id, referencedTableName=proyecto_fase		\N	3.6.3	\N	\N	4841734745
20200124174517-2	jhipster	config/liquibase/changelog/20200124174517_added_entity_constraints_Retroalimentacion.xml	2020-03-21 20:49:00.01011	136	EXECUTED	\N	addForeignKeyConstraint baseTableName=retroalimentacion, constraintName=fk_retroalimentacion_retroalimentacion_proyecto_fase_id, referencedTableName=proyecto_fase; addForeignKeyConstraint baseTableName=retroalimentacion, constraintName=fk_retroali...		\N	3.6.3	\N	\N	4841734745
20200124174518-2	jhipster	config/liquibase/changelog/20200124174518_added_entity_constraints_AdjuntoRetroalimentacion.xml	2020-03-21 20:49:00.033282	137	EXECUTED	\N	addForeignKeyConstraint baseTableName=adjunto_retroalimentacion, constraintName=adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id, referencedTableName=retroalimentacion		\N	3.6.3	\N	\N	4841734745
20200124174519-2	jhipster	config/liquibase/changelog/20200124174519_added_entity_constraints_FichaTecnica.xml	2020-03-21 20:49:00.067158	138	EXECUTED	\N	addForeignKeyConstraint baseTableName=ficha_tecnica, constraintName=fk_ficha_tecnica_ficha_tecnica_user_id, referencedTableName=jhi_user		\N	3.6.3	\N	\N	4841734745
20200319194600-2	jhipster	config/liquibase/changelog/20200319194600_added_entity_constraints_Menu.xml	2020-03-21 20:49:00.129787	140	EXECUTED	\N	addForeignKeyConstraint baseTableName=menu, constraintName=fk_menu_menu_padre_id, referencedTableName=menu		\N	3.6.3	\N	\N	4841734745
\.


                                                                                                                                                        3184.dat                                                                                            0000600 0004000 0002000 00000000075 13767147462 0014274 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	t	2020-10-08 20:05:53.204	ITPLAP-06-PC (192.168.56.1)
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                   3185.dat                                                                                            0000600 0004000 0002000 00000035262 13767147462 0014303 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        21757	Funciones a Realizar por Parte del Estudiante	4101	20651	Son aquellas funciones que encomienda la empresa al estudiante, las cuales guardan relación con el convenio firmado.
4252	Título	4101	1551	Debe ser claro y corto como para despertar el interés del lector a partir de una palabra clave, y lo suficientemente directo para delimitar los alcances y no crear falsas expectativas sobre su contenido. 
4253	Resumen Ejecutivo	4101	1551	Debe contener la información necesaria para darle al lector una idea precisa de la pertinencia y calidad del proceso investigativo. Este debe contener una síntesis del problema a investigar, el marco teórico, objetivos a utilizar y resultados esperados
4254	Planteamiento del Problema y Pregunta de Investigación (o Hipótesis)	4101	1551	Describir de forma precisa y completa la naturaleza y magnitud de la situación problema. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas.\nSe debe finalizar con la hipótesis, pregunta o problema de investigación que se quiere responder; este atiende al contexto en el cual se pretende lograr un entendimiento, una aplicación o una solución con la ejecución del proyecto\n
4255	Justificación en Términos de Necesidades y Pertinencia	4101	1551	Justificar la necesidad o importancia de la investigación en función de la generación de nuevos conocimientos o, del aporte al desarrollo de la región o de su pertinencia a nivel nacional
4256	Objetivo General	4101	1551	El objetivo debe guardar una estrecha relación con el título, el problema, pregunta o con la hipótesis que se quieren resolver; según el tipo de investigación y/o modalidad de trabajo de grado. Se sugiere no formular más de un (1) objetivo general.
4257	Objetivos Específicos	4101	1551	Los objetivos específicos permiten alcanzar el objetivo general; son las metas a mediano y corto plazo que permiten resolver el problema, refutar o apoyar la hipótesis, formular una idea grande de desarrollo tecnológico, etc. \nNo se deben confundir objetivos con actividades o procedimientos metodológicos.\n
4258	Marco de Referencia (Teórico, Conceptual, Contextual y Legal)	4101	1551	Deberá responder a las siguientes demandas: síntesis del contexto teórico general en el cual se ubica el tema de la propuesta, estado actual del conocimiento del problema (nacional y mundial), autores, conceptos pertinentes y enfoque teórico que orienten la investigación, brechas que existen y vacíos que se quieren llenar con el proyecto; ¿por qué? y ¿cómo? la investigación propuesta con fundamento en investigaciones previas, contribuirá con posibilidades de éxito, a la solución o comprensión del problema planteado o al desarrollo del sector de aplicación interesado, constituyéndose un elemento esencial bajo la normatividad colombiana. 
4259	Metodología	4101	1551	Con este se deberá mostrar, la forma en la que serán alcanzados cada uno de los objetivos propuestos. Así mismo, reflejar la estructura lógica y el rigor científico del proceso de investigación. Deben detallarse los procedimientos, técnicas, actividades y demás estrategias metodológicas requeridas para la investigación. Deberá indicarse el proceso a seguir en la recolección de la información, así como en la organización, sistematización y análisis de los datos. 
21752	Planteamiento del Problema	4101	20651	Describir de forma precisa y completa la naturaleza y magnitud de la situación problema. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas. Se debe finalizar con la hipótesis, pregunta o problema de investigación que se quiere responder; este atiende al contexto en el cual se pretende lograr un entendimiento, una aplicación o una solución con la ejecución del proyecto.  
21753	Justificación	4101	20651	Hacer una descripción precisa y completa de la naturaleza y magnitud del proyecto de pasantía, así como justificar la necesidad o importancia del desarrollo de la pasantía en función de la generación de nuevos conocimientos o la solución a un problema o necesidad. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas.
21754	Objetivo General	4101	20651	El objetivo debe guardar una estrecha relación con el título, el problema, pregunta o con la hipótesis que se quieren resolver; según el tipo de investigación y/o modalidad de trabajo de grado. Se sugiere no formular más de un (1) objetivo general.
21755	Objetivos Específicos	4101	20651	Los objetivos específicos permiten alcanzar el objetivo general; son las metas a mediano y corto plazo que permiten resolver el problema, refutar o apoyar la hipótesis, formular una idea grande de desarrollo tecnológico, etc.  No se deben confundir objetivos con actividades o procedimientos metodológicos. Mínimo tres (3), máximo cuatro (4) 
21756	Metodologóa	4101	20651	Con éste se deberá mostrar, la forma en la que serán alcanzados cada uno de los objetivos propuestos. Así mismo, reflejar la estructura lógica y el rigor científico del proceso de investigación.  Deben detallarse los procedimientos, técnicas, actividades y demás estrategias metodológicas requeridas para la investigación. Deberá indicarse el proceso a seguir en l a recolección de la información, así como en la organización, sistematización y análisis de los datos. Máximo tres mil (3.000) palabras.
21758	Resultados Esperados	4101	20651	Se presentan los posibles resultados o productos que se esperan alcanzar con la realización de la pasantía. Estos deben ser coherentes con los objetivos específicos y las funciones a realizar.
21751	Título de la propuesta de Pasantía	4101	20651	Debe ser claro y corto como para despertar el interés del lector a partir de una palabra clave, y lo suficientemente directo para delimitar los alcances y no crear falsas expectativas sobre su contenido. Máximo doce (12) palabras
22051	Titulo del Artículo	4101	22001	(No mayor a 25 palabras. Ubicar al final del título: Estudio de caso)
22052	Resumen	4101	22001	El resumen debe tener como mínimo 200 palabras y como máximo 250 palabras, no puede contener ecuaciones, figuras, siglas, tablas ni referencias. Debe presentar en forma resumida la pregunta de reflexión, los métodos de recolección y análisis de información, las principales lecciones y recomendaciones. Palabras clave: El autor debe proporcionar palabras clave (en orden alfabético), un mínimo de 3 y un máximo de 6, que ayuden a identificar los temas o aspectos principales del artículo.
22053	Abstract	4101	22001	Debe contener la traducción del resumen en idioma inglés (traducción de alta calidad).
22054	Keywords	4101	22001	Contiene la traducción de las palabras claves al idioma inglés.
22055	Introducción	4101	22001	Incluye la presentación, su propósito y justificación, planteamiento de las preguntas que orientan el Estudio de Caso y de sus antecedentes.
22056	Las preguntas de reflexión	4101	22001	En esta parte se presentan las preguntas que son el eje de reflexión del Estudio de Caso. Si el o los autores(es) lo consideran necesario pueden incorporar una breve descripción de la literatura sobre el tema que ayude a contextualizar la pregunta y las conclusiones y recomendaciones del mismo.
22057	Métodos de recolección y análisis de información	4101	22001	Se debe describir brevemente los elementos del diseño de la investigación.
22058	Narración del Estudio de Caso	4101	22001	Redactar el Estudio de Caso teniendo en cuenta la audiencia del mismo, utilizando un lenguaje claro y simple de forma que pueda ser comprendido por personas externas al mismo; escribir párrafos cortos y evitar el uso excesivo de acrónimos; incluir citas y referencias cuando sea pertinente; y reconocer la participación y el esfuerzo de los individuos o equipos que hicieron posible el análisis y documentación de sus procesos o experiencias; y limitar su extensión a un máximo de 10 a 15 páginas. Se recomienda hacer uso del método narrativo para describir el Estudio de Caso. Este método permite explicar el flujo de acciones del Estudio de Caso dentro de su contexto o circunstancias específicas en las cuales éstas ocurrieron. El producto de esta narración es el relato, que organiza en una secuencia coherente los acontecimientos en función de su contribución al desarrollo del Estudio de Caso, y de las respuestas a las preguntas de reflexión. De esta forma, el Estudio de Caso lleva al lector a apreciar el proceso o experiencia con una viveza y detalle que no está presente en otros tipos de análisis. La narración se realiza con base en los hitos o momentos críticos en el desarrollo del proceso o experiencia analizados, los actores que formaron parte del mismo (personas, organizaciones o instituciones), y los resultados alcanzados. Estos momentos críticos del caso deben analizarse a la luz de las siguientes preguntas: ¿qué funcionó bien y por qué?, ¿qué pudo haber funcionado mejor y por qué? El relato debe profundizar en las causas de los éxitos y de los obstáculos enfrentados.
22059	Lecciones y recomendaciones	4101	22001	Las lecciones y recomendaciones deben aportar elementos para responder a las preguntas iniciales del caso, que a su vez parten de la hipótesis de trabajo del proceso o experiencia analizados. A lo largo de su desarrollo, el relato del Estudio de Caso ha venido presentando al lector la evidencia e información en las que se sustentan estas lecciones y recomendaciones. Las lecciones aprendidas pueden definirse como el conocimiento adquirido sobre un proceso, una o varias experiencias a través de la reflexión y el análisis crítico. Los resultados de esta experiencia o proceso pueden ser positivos o negativos (fortalezas y debilidades en el diseño o implementación de un proyecto). Las lecciones deben expresar las relaciones entre el resultado de una experiencia y los factores críticos o condiciones que pueden haber incidido sobre su éxito o lo obstaculizaron. Las lecciones aprendidas deben ser propositivas (sugerir recomendaciones), y deben permitir identificar tendencias y relaciones causa-efecto para un contexto específico. El objetivo de las lecciones es ofrecer una comprensión de la experiencia o procesos analizados, y el de las recomendaciones es ofrecer orientaciones prácticas, accionables y replicables. Estas lecciones y recomendaciones pueden presentarse agrupadas en temáticas comunes. Cada lección o recomendación debe estar redactada de una manera concreta, y proporcionar suficientes elementos al lector para que este comprenda las razones que la sustentan. Como resultado de las respuestas a las preguntas de reflexión y como producto de la narración del Estudio de Caso, las recomendaciones deberán tratar de responder a la pregunta: ¿qué podría  hacerse diferente y mejor en una próxima ocasión?
22060	Anexos	4101	22001	Para facilitar la lectura del Estudio de Caso se sugiere soportar la investigación con información cualitativa y cuantitativa como los métodos e instrumentos de recolección y análisis utilizados (excepto en casos de confidencialidad).
22061	Bibliografía	4101	22001	Aquí la lista de los autores citados  dentro del texto, utilizando el administrador de fuentes, conforme a la Norma APA última versión, se caracterizan por ser pertinentes, válidas, veraces y coherentes con ser pertinentes, válidas, veraces y coherentes con el tema seleccionado
22351	Título Provisional	4101	22002	Debe ser claro y corto como para despertar el interés del lector a partir de una palabra clave, y lo suficientemente directo para delimitar los alcances y no crear falsas expectativas sobre su contenido. Máximo doce (12) palabras 
22352	Resumen Ejecutivo	4101	22002	Debe contener la información necesaria para darle al lector una idea precisa de la pertinencia y calidad del proceso investigativo. Este debe contener una síntesis del problema a investigar, el marco teórico, objetivos a utilizar y resultados esperados. Máximo quinientas (500) palabras
22353	 Planteamiento del Problema y Pregunta de Investigación (o Hipótesis):	4101	22002	Describir de forma precisa y completa la naturaleza y magnitud de la situación problema. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas. Se debe finalizar con la hipótesis, pregunta o problema de investigación que se quiere responder; este atiende al contexto en el cual se pretende lograr un entendimiento, una aplicación o una solución con la ejecución del proyecto.  
22354	Justificación en Términos de Necesidades y Pertinencia:	4101	22002	Justificar la necesidad o importancia de la investigación en función de la generación de nuevos conocimientos o, del aporte al desarrollo de la región o de su pertinencia a nivel nacional. Máximo trecientas (300) palabras
22355	 Objetivo General	4101	22002	El objetivo debe guardar una estrecha relación con el título, el problema, pregunta o con la hipótesis que se quieren resolver; según el tipo de investigación y/o modalidad de trabajo de grado. Se sugiere no formular más de un (1) objetivo general.
22356	Objetivos Específicos	4101	22002	Los objetivos específicos permiten alcanzar el objetivo general; son las metas a mediano y corto plazo que permiten resolver el problema, refutar o apoyar la hipótesis, formular una idea grande de desarrollo tecnológico, etc.  No se deben confundir objetivos con actividades o procedimientos metodológicos. Mínimo tres (3), máximo cuatro (4)
22357	 Marco de Referencia (Teórico, Conceptual, Contextual y Legal)	4101	22002	Deberá responder a las siguientes demandas: síntesis del contexto teórico general en el cual se ubica el tema de la propuesta, estado actual del conocimiento del problema (nacional y mundial), autores, conceptos pertinentes y enfoque teórico que orienten la investigación, brechas que existen y vacíos que se quieren llenar con el proyecto; ¿por qué? y ¿cómo? la investigación propuesta con fundamento en investigaciones previas, contribuirá con posibilidades de éxito, a la solución o comprensión del problema planteado o al desarrollo del sector de aplicación interesado, constituyéndose un elemento esencial bajo la normatividad colombiana. 
22358	Metodología	4101	22002	Con este se deberá mostrar, la forma en la que serán alcanzados cada uno de los objetivos propuestos. Así mismo, reflejar la estructura lógica y el rigor científico del proceso de investigación.  Deben detallarse los procedimientos, técnicas, actividades y demás estrategias metodológicas requeridas para la investigación. Deberá indicarse el proceso a seguir en la recolección de la información, así como en la organización, sistematización y análisis de los datos. Máximo tres mil (3.000) palabras 
25051	pregunta de investigacion	4101	20651	una pregunta de investigacion es ...
\.


                                                                                                                                                                                                                                                                                                                                              3186.dat                                                                                            0000600 0004000 0002000 00000036317 13767147462 0014306 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30009	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4256	29954	El objetivo debe guardar una estrecha relación con el título, el problema, pregunta o con la hipótesis que se quieren resolver; según el tipo de investigación y/o modalidad de trabajo de grado. Se sugiere no formular más de un (1) objetivo general.
30010	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4257	29954	Los objetivos específicos permiten alcanzar el objetivo general; son las metas a mediano y corto plazo que permiten resolver el problema, refutar o apoyar la hipótesis, formular una idea grande de desarrollo tecnológico, etc. \nNo se deben confundir objetivos con actividades o procedimientos metodológicos.\n
30011	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4255	29954	Justificar la necesidad o importancia de la investigación en función de la generación de nuevos conocimientos o, del aporte al desarrollo de la región o de su pertinencia a nivel nacional
30012	What is Lorem Ipsum?	4252	29954	Debe ser claro y corto como para despertar el interés del lector a partir de una palabra clave, y lo suficientemente directo para delimitar los alcances y no crear falsas expectativas sobre su contenido. 
30013	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum	4253	29954	Debe contener la información necesaria para darle al lector una idea precisa de la pertinencia y calidad del proceso investigativo. Este debe contener una síntesis del problema a investigar, el marco teórico, objetivos a utilizar y resultados esperados
30014	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4254	29954	Describir de forma precisa y completa la naturaleza y magnitud de la situación problema. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas.\nSe debe finalizar con la hipótesis, pregunta o problema de investigación que se quiere responder; este atiende al contexto en el cual se pretende lograr un entendimiento, una aplicación o una solución con la ejecución del proyecto\n
30015	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4258	29954	Deberá responder a las siguientes demandas: síntesis del contexto teórico general en el cual se ubica el tema de la propuesta, estado actual del conocimiento del problema (nacional y mundial), autores, conceptos pertinentes y enfoque teórico que orienten la investigación, brechas que existen y vacíos que se quieren llenar con el proyecto; ¿por qué? y ¿cómo? la investigación propuesta con fundamento en investigaciones previas, contribuirá con posibilidades de éxito, a la solución o comprensión del problema planteado o al desarrollo del sector de aplicación interesado, constituyéndose un elemento esencial bajo la normatividad colombiana. 
30016	Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4259	29954	Con este se deberá mostrar, la forma en la que serán alcanzados cada uno de los objetivos propuestos. Así mismo, reflejar la estructura lógica y el rigor científico del proceso de investigación. Deben detallarse los procedimientos, técnicas, actividades y demás estrategias metodológicas requeridas para la investigación. Deberá indicarse el proceso a seguir en la recolección de la información, así como en la organización, sistematización y análisis de los datos. 
31102	The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.	4255	30951	Justificar la necesidad o importancia de la investigación en función de la generación de nuevos conocimientos o, del aporte al desarrollo de la región o de su pertinencia a nivel nacional
31106	Where can I get some?\nThere are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.	4257	30951	Los objetivos específicos permiten alcanzar el objetivo general; son las metas a mediano y corto plazo que permiten resolver el problema, refutar o apoyar la hipótesis, formular una idea grande de desarrollo tecnológico, etc. \nNo se deben confundir objetivos con actividades o procedimientos metodológicos.\n
31101	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.	4253	30951	Debe contener la información necesaria para darle al lector una idea precisa de la pertinencia y calidad del proceso investigativo. Este debe contener una síntesis del problema a investigar, el marco teórico, objetivos a utilizar y resultados esperados
31103	Lorem Ipsum	4252	30951	Debe ser claro y corto como para despertar el interés del lector a partir de una palabra clave, y lo suficientemente directo para delimitar los alcances y no crear falsas expectativas sobre su contenido. 
31105	It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).	4256	30951	El objetivo debe guardar una estrecha relación con el título, el problema, pregunta o con la hipótesis que se quieren resolver; según el tipo de investigación y/o modalidad de trabajo de grado. Se sugiere no formular más de un (1) objetivo general.
31104	Where does it come from?\nContrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.	4254	30951	Describir de forma precisa y completa la naturaleza y magnitud de la situación problema. Se debe hacer desde la problemática observada, investigaciones anteriores y referencias normativas.\nSe debe finalizar con la hipótesis, pregunta o problema de investigación que se quiere responder; este atiende al contexto en el cual se pretende lograr un entendimiento, una aplicación o una solución con la ejecución del proyecto\n
31107	The standard Lorem Ipsum passage, used since the 1500s\n"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."	4258	30951	Deberá responder a las siguientes demandas: síntesis del contexto teórico general en el cual se ubica el tema de la propuesta, estado actual del conocimiento del problema (nacional y mundial), autores, conceptos pertinentes y enfoque teórico que orienten la investigación, brechas que existen y vacíos que se quieren llenar con el proyecto; ¿por qué? y ¿cómo? la investigación propuesta con fundamento en investigaciones previas, contribuirá con posibilidades de éxito, a la solución o comprensión del problema planteado o al desarrollo del sector de aplicación interesado, constituyéndose un elemento esencial bajo la normatividad colombiana. 
31108	Section 1.10.32 of "de Finibus Bonorum et Malorum", written by Cicero in 45 BC\n"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"	4259	30951	Con este se deberá mostrar, la forma en la que serán alcanzados cada uno de los objetivos propuestos. Así mismo, reflejar la estructura lógica y el rigor científico del proceso de investigación. Deben detallarse los procedimientos, técnicas, actividades y demás estrategias metodológicas requeridas para la investigación. Deberá indicarse el proceso a seguir en la recolección de la información, así como en la organización, sistematización y análisis de los datos. 
\.


                                                                                                                                                                                                                                                                                                                 3187.dat                                                                                            0000600 0004000 0002000 00000000566 13767147462 0014304 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Corpoamazonía	Shoes Intuitive
2	Naciones Unidas	Portugal Persistent
3	Cootep	Idaho strategize
4	Bancolombia	Connecticut Avon Money Market Account
5	Gran Tierra	Security Virginia
6	Empresa de Energía del Putumayo	Maine Checking Account markets
7	Aguas Mocoa	Concrete
8	Cámara de Comercio	FTP
9	Dane	Handmade Plastic Mouse neural
10	Cooperativa Utrahuilca	synergize
\.


                                                                                                                                          3188.dat                                                                                            0000600 0004000 0002000 00000000115 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30251	5000000	t	1	29954
31301	323244	t	1	30951
31302	43423443	t	8	30951
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                   3189.dat                                                                                            0000600 0004000 0002000 00000000131 13767147462 0014272 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        4753	1	Ingeniería y Ciencias Básicas
25001	2	Administración y Ciencias Contables
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                       3190.dat                                                                                            0000600 0004000 0002000 00000000472 13767147462 0014272 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	copying benchmark Sausages	t	\N
2	solid state	f	\N
3	bypass Paradigm Chicken	t	\N
4	hard drive	f	\N
5	Generic Steel Car SCSI	t	\N
6	Yemeni Rial Shoals deposit	t	\N
7	utilize Small Fresh Gloves target	t	\N
8	Public-key Grocery Ball	f	\N
9	Group Practical GB	t	\N
10	calculating conglomeration bypassing	t	\N
\.


                                                                                                                                                                                                      3191.dat                                                                                            0000600 0004000 0002000 00000001323 13767147462 0014267 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	JSON	virtual deposit	Squares mint green e-business	\N
2	Ghana back-end National	Direct	Profit-focused	\N
3	Jewelery cutting-edge	Games	Iraq Bedfordshire	\N
4	Concrete 24/7 Directives	sky blue	Kenyan Shilling robust	\N
5	Versatile Exclusive Moroccan Dirham	capability zero tolerance Computers	Analyst paradigms	\N
6	encompassing	deposit Canadian Dollar Lebanon	Designer Persistent	\N
7	Handmade 24/7 Re-contextualized	web-enabled invoice withdrawal	bypassing optical	\N
8	installation	Argentine Peso	Kentucky Algerian Dinar Clothing	\N
9	open-source turn-key	Sausages	disintermediate Cambridgeshire hacking	\N
10	bricks-and-clicks Concrete Global	Fiji Dollar Profound Bacon	Macao Equatorial Guinea Vision-oriented	\N
\.


                                                                                                                                                                                                                                                                                                             3192.dat                                                                                            0000600 0004000 0002000 00000000060 13767147462 0014265 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        4101	Prueba de formato	1.0.0	P1	2020-05-11
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                3193.dat                                                                                            0000600 0004000 0002000 00000000057 13767147462 0014274 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        6551	Prueba de semillero 	f
0	No Aplica	f
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 3194.dat                                                                                            0000600 0004000 0002000 00000000205 13767147462 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30201	eeee	2	ddd	ddd	29954	1
31251	impacto2	1	cantidad de estudiantes beneficiados	ddd	30951	3
31252	impacto|	1	ddd	ddd	30951	2
\.


                                                                                                                                                                                                                                                                                                                                                                                           3195.dat                                                                                            0000600 0004000 0002000 00000001122 13767147462 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        640	Vereda las Palmas	aizquierdo@itp.edu.co	t	f	f	f	t	t	f	t	f	t	f	fotocopias	cootep	810-5020	cr 12a 14-32	terciario	Raquel Malua	francisco perez	supervisor	asesorcootep@hotmail.com	Mocoa	42055282	cootepltda@hotmail.com	putumayo	1124857906	administrador 	3105581320	21602	20724	\N
640	calle 12 12 12	aizquierdo@itp.edu.co	t	f	f	f	f	f	f	t	t	t	f	\N	Corpoamazonía	434-4343-4	calle 12 12 12	Agricola	María Piagot Alarcón Muñoz	francisco perez	supervisor	asesorcootep@hotmail.com	Mocoa	42055282	cootepltda@hotmail.com	Putumayo	1124857906	administrador de empresas	3105581320	29265	24201	100
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                              3196.dat                                                                                            0000600 0004000 0002000 00000000443 13767147462 0014276 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        29871	\N	\N	10951	29954	10751
29870	\N	\N	2001	29954	10751
29872	\N	\N	1951	29954	10752
29869	\N	\N	\N	29954	4451
29873	\N	\N	1951	29954	4542
29874	\N	\N	30351	29954	4542
31002	\N	\N	10951	30951	10751
31004	\N	\N	1951	30951	10752
31003	\N	\N	31051	30951	10751
31001	\N	\N	\N	30951	4451
\.


                                                                                                                                                                                                                             3222.dat                                                                                            0000600 0004000 0002000 00000000154 13767147462 0014263 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        25902	Teorica	string	proposito	string
25901	aplicada	La investigacion aplicada es ...	proposito	string
\.


                                                                                                                                                                                                                                                                                                                                                                                                                    3197.dat                                                                                            0000600 0004000 0002000 00000000133 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        ROLE_ADMIN
ROLE_USER
ROLE_ASESOR
ROLE_DOCENTE
ROLE_CIECYT
ROLE_ESTUDIANTE
ROLE_JURADO
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                     3198.dat                                                                                            0000600 0004000 0002000 00000005375 13767147462 0014311 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        29801	viviana	2020-11-13 16:19:09.159	AUTHENTICATION_SUCCESS
29803	viviana	2020-11-13 16:19:12.29	AUTHENTICATION_SUCCESS
29804	viviana	2020-11-13 16:19:13.227	AUTHENTICATION_SUCCESS
29808	martin	2020-11-13 16:46:56.159	AUTHENTICATION_FAILURE
29809	martin	2020-11-13 16:47:04.393	AUTHENTICATION_FAILURE
29810	martin	2020-11-13 16:47:12.332	AUTHENTICATION_FAILURE
29811	martin	2020-11-13 16:47:26.15	AUTHENTICATION_FAILURE
29812	martin	2020-11-13 16:48:03.788	AUTHENTICATION_FAILURE
29813	admin	2020-11-13 16:48:32.763	AUTHENTICATION_SUCCESS
29814	martin	2020-11-13 16:50:17.548	AUTHENTICATION_FAILURE
29815	adrian	2020-11-13 16:51:44.951	AUTHENTICATION_SUCCESS
29816	admin	2020-11-13 16:53:27.646	AUTHENTICATION_SUCCESS
29818	viviana	2020-11-13 22:46:06.611	AUTHENTICATION_SUCCESS
29822	viviana	2020-11-14 00:40:06.129	AUTHENTICATION_SUCCESS
29823	maria	2020-11-14 00:48:14.589	AUTHENTICATION_SUCCESS
29826	admin	2020-11-14 01:10:42.861	AUTHENTICATION_SUCCESS
30401	admin	2020-11-20 20:31:28.914	AUTHENTICATION_SUCCESS
30551	admin	2020-11-24 17:16:25.879	AUTHENTICATION_SUCCESS
30602	admin	2020-11-25 16:17:50.481	AUTHENTICATION_SUCCESS
30652	admin	2020-11-28 03:03:44.433	AUTHENTICATION_SUCCESS
30751	viviana	2020-11-29 01:33:13.737	AUTHENTICATION_SUCCESS
30802	adrian	2020-11-30 03:37:08.416	AUTHENTICATION_SUCCESS
30901	admin	2020-12-01 19:57:24.486	AUTHENTICATION_SUCCESS
30902	viviana	2020-12-01 19:58:49.451	AUTHENTICATION_SUCCESS
30903	rosa	2020-12-01 20:02:58.644	AUTHENTICATION_FAILURE
30904	admin	2020-12-01 20:03:22.306	AUTHENTICATION_SUCCESS
30906	carmen	2020-12-01 20:09:43.424	AUTHENTICATION_SUCCESS
30907	adrian	2020-12-01 20:18:32.603	AUTHENTICATION_SUCCESS
31401	carmen	2020-12-09 20:50:01.293	AUTHENTICATION_SUCCESS
29802	viviana	2020-11-13 16:19:09.564	AUTHENTICATION_SUCCESS
29805	viviana	2020-11-13 16:19:13.245	AUTHENTICATION_SUCCESS
29806	admin	2020-11-13 16:27:32.586	AUTHENTICATION_SUCCESS
29807	viviana	2020-11-13 16:43:20.113	AUTHENTICATION_SUCCESS
29817	viviana	2020-11-13 16:56:48.679	AUTHENTICATION_SUCCESS
29819	admin	2020-11-13 23:18:12.252	AUTHENTICATION_SUCCESS
29820	viviana	2020-11-13 23:22:31.857	AUTHENTICATION_SUCCESS
29821	maria	2020-11-13 23:24:43.068	AUTHENTICATION_SUCCESS
29824	adrian	2020-11-14 01:03:08.259	AUTHENTICATION_SUCCESS
29825	viviana	2020-11-14 01:08:22.353	AUTHENTICATION_SUCCESS
29827	adrian	2020-11-14 01:15:16.887	AUTHENTICATION_SUCCESS
30451	admin	2020-11-21 21:01:05.73	AUTHENTICATION_SUCCESS
30601	admin	2020-11-25 13:47:34.32	AUTHENTICATION_SUCCESS
30651	admin	2020-11-26 19:23:28.505	AUTHENTICATION_SUCCESS
30701	admin	2020-11-28 21:48:37.28	AUTHENTICATION_SUCCESS
30803	admin	2020-12-01 04:45:56.891	AUTHENTICATION_SUCCESS
30905	admin	2020-12-01 20:05:48.529	AUTHENTICATION_SUCCESS
30801	viviana	2020-11-29 15:31:50.199	AUTHENTICATION_SUCCESS
\.


                                                                                                                                                                                                                                                                   3199.dat                                                                                            0000600 0004000 0002000 00000001400 13767147462 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        29808	type	org.springframework.security.authentication.BadCredentialsException
29808	message	Bad credentials
29809	type	org.springframework.security.authentication.BadCredentialsException
29809	message	Bad credentials
29810	type	org.springframework.security.authentication.BadCredentialsException
29810	message	Bad credentials
29811	type	org.springframework.security.authentication.BadCredentialsException
29811	message	Bad credentials
29812	type	org.springframework.security.authentication.BadCredentialsException
29812	message	Bad credentials
29814	type	org.springframework.security.authentication.BadCredentialsException
29814	message	Bad credentials
30903	type	org.springframework.security.authentication.BadCredentialsException
30903	message	Bad credentials
\.


                                                                                                                                                                                                                                                                3200.dat                                                                                            0000600 0004000 0002000 00000005700 13767147462 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost		t	es	\N	\N	system	\N	\N	system	\N
2	anonymoususer	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost		t	es	\N	\N	system	\N	\N	system	\N
3	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost		t	es	\N	\N	system	\N	\N	system	\N
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	es	\N	\N	system	\N	\N	system	\N
1951	adrian	$2a$10$KUimWYRVR9oVFDMYCk9WPOfjWbopqCbVVKzdSqEfNVasFUXMq0iFa	Alvaro Adrian	Izquierdo Gomez	aizquierdo@itp.edu.co	\N	t	es	ab7CLIJBEUdJOb1WBscD	\N	admin	2020-03-26 15:25:22.989	\N	admin	2020-03-26 15:28:35.256
5701	ruperto	$2a$10$yr8MoBZ3.XwiUhamHTYw/OkSo3uFJpOSXqInlWeCg5pKdZU4Y8PTK	Ruperto	Revelo	rrevelo@itp.edu.co	\N	t	es	\N	yXm5tnCBcPYD0UjMtaDi	admin	2020-07-05 02:57:32.273	2020-07-05 02:57:32.02	admin	2020-07-05 02:57:32.273
2001	maria	$2a$10$tyClisdL4Yzzu689oztMfu4DBTWIZh.pgzD/OA5c4bcl.P/0AG5te	Maria	Cardenas	maria@hotmail.com	\N	t	es	rMgidO2lMK2tqCTYnGwh	\N	anonymousUser	2020-03-26 20:04:45.166	\N	admin	2020-03-26 20:05:29.723
10951	rosa	$2a$10$7odilhz3hhXPf9igttJnVu.1pEIJLrwFg.cRSlkJKbIPj2TiB7ty6	Rosa	Losa	losaro@itp.edu.co	\N	t	es	\N	BXpL3iIAkTcV2FAh8orC	admin	2020-07-21 23:07:18.771	2020-07-21 23:07:18.377	admin	2020-07-21 23:07:18.771
10952	luis	$2a$10$aNc55JZXqfCess5vyhPdcOsUp16df290/CuBthu6AmA/WVrRTTmzO	Luis Fernando	Zambrano	luisferz@itp.edu.co	\N	t	es	\N	r7BqekWOtxm14aoY1T1X	admin	2020-07-21 23:08:58.002	2020-07-21 23:08:57.912	admin	2020-07-21 23:08:58.002
20301	lorena	$2a$10$Y8xBhVuNIoPj4bisQDilDObURqeB2ZHnLVWPDpsERBgYwDr9tW6dK	Lorena 	Agudelo	lorena.agudelo@itp.edu.co	\N	t	es	\N	XFwB6Z1zcmZ8APUsmwHQ	admin	2020-08-17 16:47:41.202	2020-08-17 16:47:40.942	admin	2020-08-17 16:47:41.202
24301	viviana	$2a$10$3izv9cIoew5iAk4eqDj.1uQCn9nWP3W55/z9qKOo56y7AV9juAciW	\N	\N	viviana@itp.edu.co	\N	t	es	MVBQ6C8dJarUpr9AkdGq	\N	anonymousUser	2020-09-20 20:59:36.455	\N	admin	2020-09-20 21:03:36.03
24852	eisy	$2a$10$1cjHZEYEunm/co4mWpkUP.AuQQGj/ZZxR2kzNRhIPcCpfOxJF0pH.	Eisy Yulieth 	Cano Martínez	eisyyulieth@hotmail.com	\N	t	es	ZY7nWuL4wY9tjOKwu5rm	\N	anonymousUser	2020-09-28 18:16:20.079	\N	admin	2020-09-28 18:18:54.539
5551	martin	$2a$10$q7DYY1tHo4yeSDg6ZcQMc.YfpIAZnhFaF1oQtKQmXI7hUoa4xfdvy	Martín Javier	 Caicedo	mcaicedo@itp.edu.co	\N	t	es	\N	1K3NYt5O0MRdmE2Og8N0	admin	2020-07-04 00:31:03.081	2020-11-13 16:50:33.068	anonymousUser	2020-11-13 16:50:33.587
30351	juan	$2a$10$JelnVNuE.Xn.SSvU9kQsLuZxmNzJA4ZQXtzKMixoll7YfOf1bLGGa	Juan Fernando 	Revelo	juan.revelo@itp.edu.co	\N	t	es	\N	PxenK0Q4VXAR8gvWAEOw	admin	2020-11-14 01:13:28.447	2020-11-14 01:13:28.349	admin	2020-11-14 01:13:28.447
31051	carmen	$2a$10$Cl.ix1qhNoGKFqYu09KaU.AAujjKSXkZuJTErVvF4wNX9dL9yx1ji	Carmen	Jimenez	carmen@itp.edu.co	\N	t	es	IjsuieDhIkbmuMWhFQJ8	\N	anonymousUser	2020-12-01 20:05:18.772	\N	admin	2020-12-01 20:05:58.622
\.


                                                                3201.dat                                                                                            0000600 0004000 0002000 00000000731 13767147462 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
2001	ROLE_ESTUDIANTE
5551	ROLE_ASESOR
5701	ROLE_ASESOR
10951	ROLE_ESTUDIANTE
10952	ROLE_ESTUDIANTE
1951	ROLE_ASESOR
1951	ROLE_JURADO
20301	ROLE_ASESOR
24301	ROLE_CIECYT
5551	ROLE_JURADO
2001	ROLE_USER
24301	ROLE_USER
20301	ROLE_USER
10952	ROLE_USER
5551	ROLE_USER
1951	ROLE_USER
5701	ROLE_USER
24852	ROLE_USER
24852	ROLE_ESTUDIANTE
30351	ROLE_ASESOR
30351	ROLE_JURADO
31051	ROLE_USER
31051	ROLE_ESTUDIANTE
\.


                                       3202.dat                                                                                            0000600 0004000 0002000 00000000114 13767147462 0014255 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        5301	Sub linea de prueba	11	4801	27101
4801	Linea de prueba	1	\N	27101
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                    3203.dat                                                                                            0000600 0004000 0002000 00000002531 13767147462 0014263 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1058	Cerrar Sesión	logout()	sign-out-alt	t	1055	f	\N
1057	Contraseña	/account/password	lock	t	1055	f	\N
1451	Pendientes Ubicar	/pendientes	sign-in-alt	t	\N	f	4
1453	Acuerdo	/pendiente/acuerdo	\N	t	1451	f	1
1454	Ciclo Propedeutico	/pendiente/ciclo-propedeutico	\N	t	1451	f	2
1455	Modalidad	/pendiente/modalidad	\N	t	1451	f	2
2151	Api	/admin/docs		t	1052	f	3
2152	Roles	/admin/roles	user	t	1052	f	4
4201	Elementos	/pendientes/elementos	\N	t	1451	f	\N
4051	Formatos	/pendiente/formatos/	\N	t	1451	f	\N
4202	Tipos de pregunta	/pendientes/tipos-pregunta	\N	t	1451	f	\N
4401	Roles de modalidad	/pendientes/roles-modalidad	\N	t	1451	f	\N
1056	Ajustes	/account/settings	wrench	t	1055	f	\N
1051	Inicio	/	home	t	\N	f	\N
1052	Administración	/admin	user-plus	t	\N	f	\N
1053	Aplicaciones	/admin/aplicaciones	bars	t	1052	f	\N
1054	Gestión de Usuarios	/admin/user-management	user	t	1052	f	\N
1055	Cuenta	/account	user	t	\N	f	\N
4402	Preguntas	/pendientes/preguntas	\N	t	1451	f	\N
4702	Lineas de investigación	/pendientes/linea-investigacion	\N	t	1451	f	\N
6451	Grupo investigación	/pendientes/grupo-semillero	\N	t	1451	f	10
6452	Proyectos	/pendientes/proyecto	\N	t	1451	f	11
4701	Facultad	/pendientes/facultad	\N	t	1451	f	\N
6501	Diligenciar Propuesta	/propuesta/informacion-general/{idProyecto}	\N	t	6500	f	101
6500	Proyecto	/propuesta/informacion-general	book	t	\N	f	100
\.


                                                                                                                                                                       3204.dat                                                                                            0000600 0004000 0002000 00000000131 13767147462 0014256 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        20651	Pasantía	1501	f
1551	Tesis	1501	t
22001	Diplomado	1501	f
22002	Línea	1501	t
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                       3205.dat                                                                                            0000600 0004000 0002000 00000012534 13767147462 0014271 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        4501	Título Provisional	Título Provisional	Es la frase corta más importante de la propuesta; es el primer contacto del Jurado lector con lo que es el proyecto; ¿Es claro, preciso y completo? ¿Deja claro los objetivos y variables centrales dentro de la propuesta de investigación? 	4301	1551	10752	\N	4252	\N	\N
22651	Indice	Indice	¿Incluye los capítulos y subcapítulos, temas y subtemas que son y forman parte de la totalidad de la propuesta de grado?	4301	1551	10752	\N	\N	\N	\N
22653	Introducción	\N	¿La introducción contiene una descripción del problema a abordar, sus antecedentes y la forma en la que se realizará la investigación? 	4301	1551	10752	\N	\N	\N	\N
22652	Resumen	Su extensión es de 50 a 200 palabras, debe ir en español e inglés.  	¿Expresa en forma precisa y breve, los aspectos relevantes del trabajo, como objetivos, metodología, resultados generales? 	4301	1551	10752	Resumen	4253	\N	\N
22751	Planteamiento Del Problema y Pregunta de Investigación (o Hipótesis)	\N	¿Expresa en términos claros y precisos el problema de investigación? ¿Tiene planteadas preguntas o hipótesis que permitirán resolver el proceso de investigación?	4301	1551	10752	Problema	4254	\N	\N
22752	Justificación	\N	¿Se justifica la necesidad e importancia del proceso investigativo, de acuerdo al planteamiento del problema?	4301	1551	10752	Justificacion	4255	\N	\N
22753	Objetivo General	\N	¿Establece la intención general del investigador para resolver el problema encontrado y/o la implementación de un modelo, dispositivo tecnológico, etc.? ¿Tiene claridad y puntualidad respecto a las finalidades de la experiencia investigativa?	4301	1551	10752	Objetivo General	4256	\N	\N
22754	Objetivos Específicos	\N	¿Tiene definidas las metas específicas para alcanzar el logro del objetivo general, resolver el problema o evaluar el proceso de implementación?	4301	1551	10752	Objetivos Especificos	4257	\N	\N
22755	Marco de Referencia (Teórico, Conceptual, Contextual y Legal)	\N	¿Establece los referentes teóricos que servirán de soporte para intervenir, analizar e interpretar la información? ¿Tiene definidos los antecedentes conceptuales sobre el problema de investigación? ¿Describe y argumenta referentes contextuales y legales? 	4301	1551	10752	Marco de Referencia	4258	\N	\N
30852	Justificación	Justificación	La justificación es precisa y completa sobre la naturaleza y magnitud del problema? ¿Se justifica la necesidad o importancia de la investigación?	4302	1551	4542	\N	4255	\N	10
30853	Objetivos	\N	Están vinculados con el problema planteado? ¿Son viables, claros, concretos y medibles, de acuerdo con el estudio y los métodos?	4302	1551	4542	\N	4256	\N	10
30854	Marco de Referencia	\N	¿Responde a la síntesis del contexto teórico general en el cual se ubica el tema de la investigación? ¿Contiene conceptos pertinentes y un enfoque orientado a la  investigación? ¿Constituye un elemento esencial bajo la normatividad colombiana?	4302	1551	4542	\N	4258	\N	10
30855	Metodología	\N	¿El diseño metodologico presenta claridad, pertinencia, consistencia, validez y  confiabilidad? ¿Las técnicas e instrumentos están acordes con el tipo y enfoque de la investigación en relación con los objetivos y los resultados esperados?	4302	1551	4542	\N	4259	\N	15
22908	Problema/Pregunta de Investigación	Planteamiento del Problema o Pregunta de Investigación	¿Está bien definido el problema que se quiere investigar? ¿Es clara su justificación desde  el punto de vista académico, científico, tecnológico, social, económico y legal?	4302	1551	4542	\N	4254	\N	10
30851	Marco Teórico	Marco Teórico	¿Está la teoría actualizada y es acertada con respecto al problema que se va a estudiar?  ¿Su formulación es coherente? ¿Es clara la perspectiva teórica desde donde se ubica el problema?	4302	1551	4542	\N	4258	\N	10
30856	Resultados	Resultados/Productos esperados	¿Los resultados esperados son coherentes con los objetivos propuestos y la  metodología planteada?	4302	1551	4542	\N	\N	\N	10
30857	Impactos Esperados	\N	¿El proyecto permite la generación de conocimiento científico o aporta a la resolución de problemas concretos de la realidad? ¿Son suficientes y adecuados los mecanismos de comunicación y socialización de resultados?	4302	1551	4542	\N	\N	\N	5
30858	Cronograma de Actividades	\N	¿La secuencia de actividades se adecúa a las fases de desarrollo del proyecto? ¿La duración en cada una de las etapas es apropiada y garantiza el cumplimiento del objetivo?	4302	1551	4542	\N	\N	\N	5
30859	Referencias Bibliográficas	Evaluar de acuerdo a las normas APA	¿Las referencias bibliográficas que soportan la investigación aparecen dentro del texto, utilizando el administrador de fuentes? ¿Están conforme a lo establecido en la Norma APA?	4302	1551	4542	\N	\N	\N	10
30860	Presupuesto	\N	¿Los rubros son pertinentes? ¿Los montos son los adecuados para cumplir los objetivos  del proyecto? ¿El número de investigadores y el tiempo de dedicación son adecuados?	4302	1551	4542	\N	\N	\N	5
30861	Aspectos Eticos	\N	¿Se incluye el tratamiento ético del manejo de la información y/o de los informantes en caso de requerirlo la investigación? ¿Es adecuado a la naturaleza de proyecto?	4303	1551	4542	\N	\N	\N	\N
30862	Aporte 	\N	¿Considera que el proyecto evaluado está claramente vinculado y aporta a una agenda o línea de investigación?	4304	1551	4542	\N	\N	\N	\N
\.


                                                                                                                                                                    3206.dat                                                                                            0000600 0004000 0002000 00000000504 13767147462 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30502	nvnvbnvbn	vbnvbnvbnvbn	3	2000000	3000	1000000	11	29954	3	3
30501	sfgsfdg	sdfgsf	1	5000000	3000	1000000	9	29954	7	2
31151	salida a Bogota	Section 1.10.32 of "de Finibus Bonorum et Malorum", written by Cicero in 45 BC\n	1	2000000	\N	\N	4	30951	10	3
31152	computadores	asdfadfasdf	3	5000000	3000	1000000	1	30951	1	2
\.


                                                                                                                                                                                            3207.dat                                                                                            0000600 0004000 0002000 00000000333 13767147462 0014265 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	B2B Mouse
2	Junctions one-to-one
3	analyzing holistic
4	Architect
5	Beauty dynamic schemas
6	Pound Sterling Keyboard
7	Licensed Usability models
8	Personal Loan Account markets
9	SCSI Aruba Chair
10	drive online
\.


                                                                                                                                                                                                                                                                                                     3208.dat                                                                                            0000600 0004000 0002000 00000000005 13767147462 0014262 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3224.dat                                                                                            0000600 0004000 0002000 00000000670 13767147462 0014270 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        27101	Ingeniería de Sistemas	\N	\N	\N	\N	\N	\N	\N	\N	4753
27102	Tecnología en Gestión Empresarial y de la innovación	\N	\N	\N	\N	\N	\N	\N	\N	25001
27103	Administración de Empresas	\N	\N	\N	\N	\N	\N	\N	\N	25001
26551	Desarrollo de Software		\N	105603	103	Tecnologico	10875 	Tecnólogo en Desarrollo de Software	6	4753
29901	Tecnología en Saneamiento Ambiental	\N	\N	\N	\N	Tecnologico	\N	Tecnólogo en saneamiento ambiental	6	4753
\.


                                                                        3209.dat                                                                                            0000600 0004000 0002000 00000001013 13767147462 0014263 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        29954	microservicios con angular y spring	\N	Mocoa	\N	2020-11-19	2021-01-13	\N	\N	web microservicios	\N	4801	\N	1551	4753	5301	Teorica	referencia 1\nreferencia 2\n	27101	Ingeniería de Sistemas	\N	\N	t	t	2020-11-13 20:01:33.862-05	\N	\N	\N	\N
30951	investigacion de software en la nube	https://www.youtube.com/watch?v=BYqGXP95QLc	Mocoa	\N	2020-12-01	2021-02-10	\N	\N	adfasdfadsf asdfasdf	\N	4801	\N	1551	4753	5301	Teorica	asdfsd asdfasdf	27101	Ingeniería de Sistemas	\N	\N	\N	t	2020-12-01 15:19:44.976-05	\N	\N	\N	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     3210.dat                                                                                            0000600 0004000 0002000 00000001154 13767147462 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Profound Plastic XSS	t	2020-01-24	mindshare	\N	\N
2	District THX Coordinator	t	2020-01-24	Specialist e-services	\N	\N
3	program override red	t	2020-01-24	efficient RSS Neck	\N	\N
4	Mouse fuchsia Licensed	f	2020-01-23	connecting back up	\N	\N
5	back-end 4th generation	t	2020-01-23	Savings Account online Avon	\N	\N
6	Savings Account	t	2020-01-24	architectures	\N	\N
7	Networked	f	2020-01-24	Kentucky maximized	\N	\N
8	e-tailers Architect Chair	f	2020-01-24	disintermediate Walks Credit Card Account	\N	\N
9	payment	t	2020-01-23	solid state Handmade Granite Fish Rustic	\N	\N
10	Tasty	t	2020-01-24	Berkshire	\N	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                    3211.dat                                                                                            0000600 0004000 0002000 00000004362 13767147462 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30161	\N	\N	f	22651	29954	¿Incluye los capítulos y subcapítulos, temas y subtemas que son y forman parte de la totalidad de la propuesta de grado?	\N	\N
30162	CUMPLE	\N	f	4501	29954	Es la frase corta más importante de la propuesta; es el primer contacto del Jurado lector con lo que es el proyecto; ¿Es claro, preciso y completo? ¿Deja claro los objetivos y variables centrales dentro de la propuesta de investigación? 	\N	\N
30163	\N	\N	f	22653	29954	¿La introducción contiene una descripción del problema a abordar, sus antecedentes y la forma en la que se realizará la investigación? 	\N	\N
30164	CUMPLE	\N	f	22908	29954	¿Se describe de forma organizada, clara y precisa, cómo se alcanzará cada uno de los objetivos específicos? ¿Refleja la estructura lógica y el rigor argumentativo del proceso de investigación desde la elección de un enfoque y tipo de investigación, instrumentos y herramientas para la recolección, organización, sistematización y análisis de datos?	\N	\N
30165	CUMPLE	\N	f	22751	29954	¿Expresa en términos claros y precisos el problema de investigación? ¿Tiene planteadas preguntas o hipótesis que permitirán resolver el proceso de investigación?	\N	\N
30166	CUMPLE	\N	f	22652	29954	¿Expresa en forma precisa y breve, los aspectos relevantes del trabajo, como objetivos, metodología, resultados generales? 	\N	\N
30167	CUMPLE	\N	f	22754	29954	¿Tiene definidas las metas específicas para alcanzar el logro del objetivo general, resolver el problema o evaluar el proceso de implementación?	\N	\N
30168	CUMPLE	\N	f	22752	29954	¿Se justifica la necesidad e importancia del proceso investigativo, de acuerdo al planteamiento del problema?	\N	\N
30169	CUMPLE	\N	f	22753	29954	¿Establece la intención general del investigador para resolver el problema encontrado y/o la implementación de un modelo, dispositivo tecnológico, etc.? ¿Tiene claridad y puntualidad respecto a las finalidades de la experiencia investigativa?	\N	\N
30170	CUMPLE	\N	f	22755	29954	¿Establece los referentes teóricos que servirán de soporte para intervenir, analizar e interpretar la información? ¿Tiene definidos los antecedentes conceptuales sobre el problema de investigación? ¿Describe y argumenta referentes contextuales y legales? 	\N	\N
\.


                                                                                                                                                                                                                                                                              3212.dat                                                                                            0000600 0004000 0002000 00000000206 13767147462 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        30103	 estudiantes beneficiados	cantidad de estudiantes beneficiados	estudiantes	29954	1
31201	resultado1	indicador1	itp	30951	1
\.


                                                                                                                                                                                                                                                                                                                                                                                          3213.dat                                                                                            0000600 0004000 0002000 00000001354 13767147462 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Industrial	connect Markets Chief	2020-02-08	2020-02-08	92187	\N	\N
2	Awesome	Aruba	2020-02-07	2020-02-08	84453	\N	\N
3	compress South Georgia and the South Sandwich Islands	compelling mission-critical	2020-02-08	2020-02-08	14092	\N	\N
4	back-end	Industrial	2020-02-08	2020-02-07	39488	\N	\N
5	24 hour	overriding	2020-02-08	2020-02-08	72900	\N	\N
6	quantify Unbranded Plastic Tuna Frozen	generating Highway Ergonomic	2020-02-08	2020-02-08	66884	\N	\N
7	payment	withdrawal COM	2020-02-08	2020-02-08	11959	\N	\N
8	bus synthesize Idaho	Networked Sleek Metal Table Small	2020-02-08	2020-02-08	20171	\N	\N
9	Dynamic	knowledge base Buckinghamshire	2020-02-07	2020-02-08	24929	\N	\N
10	relationships override	Brand	2020-02-08	2020-02-08	69478	\N	\N
\.


                                                                                                                                                                                                                                                                                    3214.dat                                                                                            0000600 0004000 0002000 00000003540 13767147462 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        3235	t	f	f	f	ROLE_USER	1453
3236	t	f	f	f	ROLE_USER	1454
3237	t	f	f	f	ROLE_USER	1455
5001	t	t	t	t	ROLE_ADMIN	4051
5002	t	t	t	t	ROLE_ADMIN	4201
5003	t	t	t	t	ROLE_ADMIN	4051
5004	t	t	t	t	ROLE_ADMIN	4202
5005	t	t	t	t	ROLE_ADMIN	4051
5006	t	t	t	t	ROLE_ADMIN	4401
5007	t	t	t	t	ROLE_ADMIN	4201
5008	t	t	t	t	ROLE_ADMIN	4201
5009	t	t	t	t	ROLE_ADMIN	4402
5011	t	t	t	t	ROLE_ADMIN	4202
5010	t	t	t	t	ROLE_ADMIN	4202
5012	t	t	t	t	ROLE_ADMIN	4401
5013	t	t	t	t	ROLE_ADMIN	4401
5014	t	t	t	t	ROLE_ADMIN	4701
5017	t	t	t	t	ROLE_ADMIN	4702
5015	t	t	t	t	ROLE_ADMIN	4402
5016	t	t	t	t	ROLE_ADMIN	4402
5018	t	t	t	t	ROLE_ADMIN	4701
5019	t	t	t	t	ROLE_ADMIN	4701
5020	t	t	t	t	ROLE_ADMIN	4702
5021	t	t	t	t	ROLE_ADMIN	4702
3212	t	t	t	t	ROLE_ADMIN	1051
3213	t	t	t	t	ROLE_ADMIN	1052
3214	t	t	t	t	ROLE_ADMIN	1053
3215	t	t	t	t	ROLE_ADMIN	1054
3216	t	t	t	t	ROLE_ADMIN	1055
3217	t	t	t	t	ROLE_ADMIN	1056
3218	t	t	t	t	ROLE_ADMIN	1057
3219	t	t	t	t	ROLE_ADMIN	1058
3220	t	t	t	t	ROLE_ADMIN	1451
3221	t	t	t	t	ROLE_ADMIN	1453
3222	t	t	t	t	ROLE_ADMIN	1454
3223	t	t	t	t	ROLE_ADMIN	1455
3224	t	t	t	t	ROLE_ADMIN	2151
3225	t	t	t	t	ROLE_ADMIN	2152
3226	t	f	f	f	ROLE_USER	1051
3227	f	f	f	f	ROLE_USER	1052
3228	f	f	f	f	ROLE_USER	1053
3229	f	f	f	f	ROLE_USER	1054
3230	f	f	f	f	ROLE_USER	1055
3231	f	f	f	f	ROLE_USER	1056
3232	f	f	f	f	ROLE_USER	1057
3233	f	f	f	f	ROLE_USER	1058
3234	f	f	f	f	ROLE_USER	1451
3238	f	f	f	f	ROLE_USER	2151
3239	f	f	f	f	ROLE_USER	2152
3240	t	f	f	f	ROLE_ESTUDIANTE	1051
3241	f	f	f	f	ROLE_ESTUDIANTE	1052
3242	f	f	f	f	ROLE_ESTUDIANTE	1053
3243	f	f	f	f	ROLE_ESTUDIANTE	1054
3244	f	f	f	f	ROLE_ESTUDIANTE	1055
3245	f	f	f	f	ROLE_ESTUDIANTE	1056
3246	f	f	f	f	ROLE_ESTUDIANTE	1057
3247	f	f	f	f	ROLE_ESTUDIANTE	1058
3248	f	f	f	f	ROLE_ESTUDIANTE	1451
3249	f	f	f	f	ROLE_ESTUDIANTE	1453
3250	f	f	f	f	ROLE_ESTUDIANTE	1454
3251	f	f	f	f	ROLE_ESTUDIANTE	1455
3252	f	f	f	f	ROLE_ESTUDIANTE	2151
3253	f	f	f	f	ROLE_ESTUDIANTE	2152
\.


                                                                                                                                                                3215.dat                                                                                            0000600 0004000 0002000 00000001137 13767147462 0014267 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        4542	Jurado	2	t	1551	ROLE_JURADO
4451	Asesor	1	f	1551	ROLE_ASESOR
22206	Jurado	2	f	22002	ROLE_JURADO
22205	Asesor	1	f	22002	ROLE_ASESOR
22204	Estudiante	2	f	22002	ROLE_ESTUDIANTE
22202	Asesor	1	f	22001	ROLE_ASESOR
22201	Estudiante	2	f	22001	ROLE_ESTUDIANTE
20802	Estudiante	1	f	20651	ROLE_ESTUDIANTE
20801	Asesor	1	f	20651	ROLE_ASESOR
10751	Estudiante	2	f	1551	ROLE_ESTUDIANTE
22203	Jurado	2	t	22001	ROLE_JURADO
10752	Viabilidad	1	t	1551	ROLE_JURADO
29051	Viabilidad	1	f	22001	ROLE_JURADO
29052	Jurado	1	t	20651	ROLE_JURADO
29053	Viabilidad	1	f	20651	ROLE_JURADO
29054	Viabilidad	1	f	22002	ROLE_JURADO
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                 3216.dat                                                                                            0000600 0004000 0002000 00000000334 13767147462 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Equipos y Software
2	Personal
3	Viajes
4	Salidas de Campo
5	Materiales y Suministros
6	Servicios Técnicos
7	Publicaciones y Patentes
8	Material Bibliográfico
9	Construcciones
10	Mantenimiento
11	Administración
\.


                                                                                                                                                                                                                                                                                                    3218.dat                                                                                            0000600 0004000 0002000 00000001146 13767147462 0014272 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	t	artificial intelligence	Arizona Congolese Franc	2020-01-24	\N
2	t	Developer Books	model Facilitator Minnesota	2020-01-24	\N
3	t	Berkshire Tugrik	Steel	2020-01-24	\N
4	f	capacitor	feed	2020-01-24	\N
5	t	Handmade	Table Synergistic Officer	2020-01-24	\N
6	t	parsing Identity	Health Polarised transmitting	2020-01-23	\N
7	t	Music Data East Caribbean Dollar	Savings Account Branding navigating	2020-01-24	\N
8	t	Incredible deposit	Checking Account utilisation AI	2020-01-23	\N
9	t	primary Iowa bandwidth	withdrawal copy Dynamic	2020-01-24	\N
10	t	program virtual metrics	embrace intranet deposit	2020-01-24	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                          3219.dat                                                                                            0000600 0004000 0002000 00000000202 13767147462 0014263 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        4301	Viable (sin puntaje)	viable
4302	Nota (con puntaje)	nota
4303	Libre (sin puntaje ni viabilidad)	libre
4304	Si o No	sino
\.


                                                                                                                                                                                                                                                                                                                                                                                              3220.dat                                                                                            0000600 0004000 0002000 00000000636 13767147462 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Refined New York PNG	Keyboard Overpass
2	open-source	circuit Engineer Intelligent
3	SAS deposit	Saint Barthelemy
4	generating Savings Account Indian Rupee	haptic Macedonia
5	synthesizing	Sports back up ivory
6	navigate Croatian Kuna hack	implementation XML Liaison
7	deploy payment	SSL Savings Account reintermediate
8	Clothing Chair	Savings Account
9	Bacon evolve	Utah Mission
10	protocol Awesome	Oklahoma
\.


                                                                                                  restore.sql                                                                                         0000600 0004000 0002000 00000203005 13767147462 0015405 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 11.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE ciecyt_db;
--
-- Name: ciecyt_db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';


ALTER DATABASE ciecyt_db OWNER TO postgres;

\connect ciecyt_db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: acuerdo; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.acuerdo (
    id bigint NOT NULL,
    acuerdo character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);


ALTER TABLE public.acuerdo OWNER TO ciecyt_admin;

--
-- Name: adjunto_proyecto_fase; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.adjunto_proyecto_fase (
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


ALTER TABLE public.adjunto_proyecto_fase OWNER TO ciecyt_admin;

--
-- Name: adjunto_retroalimentacion; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.adjunto_retroalimentacion (
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


ALTER TABLE public.adjunto_retroalimentacion OWNER TO ciecyt_admin;

--
-- Name: categorizacion; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.categorizacion (
    id bigint NOT NULL,
    categoria character varying(255),
    descripcion character varying(255),
    categorizacion_proyecto_id bigint
);


ALTER TABLE public.categorizacion OWNER TO ciecyt_admin;

--
-- Name: ciclo_propedeutico; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.ciclo_propedeutico (
    id bigint NOT NULL,
    ciclo character varying(255),
    modalidad_id bigint,
    ciclo_propedeutico_acuerdo_id bigint
);


ALTER TABLE public.ciclo_propedeutico OWNER TO ciecyt_admin;

--
-- Name: cronograma; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.cronograma (
    id bigint NOT NULL,
    actividad character varying(255),
    duracion integer,
    fecha_inicio date,
    fecha_fin date,
    cronograma_proyecto_id bigint,
    orden_vista integer
);


ALTER TABLE public.cronograma OWNER TO ciecyt_admin;

--
-- Name: cronograma_ciecyt; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.cronograma_ciecyt (
    id bigint NOT NULL,
    titulo_cronograma character varying(255),
    fecha_inicio date,
    fecha_fin date,
    observaciones character varying(255),
    cronograma_ciecyt_modalidad_id bigint
);


ALTER TABLE public.cronograma_ciecyt OWNER TO ciecyt_admin;

--
-- Name: cronograma_ciecyt_fases; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.cronograma_ciecyt_fases (
    id bigint NOT NULL,
    inicio_fase date,
    fin_fase date,
    texto_explicativo character varying(255),
    cronograma_ciecyt_fases_cronograma_ciecyt_id bigint,
    cronograma_ciecyt_fases_fases_id bigint
);


ALTER TABLE public.cronograma_ciecyt_fases OWNER TO ciecyt_admin;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.databasechangelog (
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


ALTER TABLE public.databasechangelog OWNER TO ciecyt_admin;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO ciecyt_admin;

--
-- Name: elemento; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.elemento (
    id bigint NOT NULL,
    elemento character varying(255),
    elemento_formato_id bigint,
    elemento_modalidad_id bigint,
    descripcion text
);


ALTER TABLE public.elemento OWNER TO ciecyt_admin;

--
-- Name: elemento_proyecto; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.elemento_proyecto (
    id bigint NOT NULL,
    dato text,
    elemento_proyecto_elemento_id bigint,
    elemento_proyecto_proyecto_id bigint,
    elemento_proyecto_proyecto_descripcion text
);


ALTER TABLE public.elemento_proyecto OWNER TO ciecyt_admin;

--
-- Name: entidad; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.entidad (
    id bigint NOT NULL,
    entidad character varying(255),
    nit character varying(255)
);


ALTER TABLE public.entidad OWNER TO ciecyt_admin;

--
-- Name: entidad_financiadora; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.entidad_financiadora (
    id bigint NOT NULL,
    valor double precision,
    aprobada boolean,
    entidad_financiadora_entidad_id bigint,
    entidad_financiadora_proyecto_id bigint
);


ALTER TABLE public.entidad_financiadora OWNER TO ciecyt_admin;

--
-- Name: facultad; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.facultad (
    id bigint NOT NULL,
    codigo_facultad character varying(255),
    facultad character varying(255)
);


ALTER TABLE public.facultad OWNER TO ciecyt_admin;

--
-- Name: fases; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.fases (
    id bigint NOT NULL,
    fase character varying(255),
    notificable boolean,
    fases_modalidad_id bigint
);


ALTER TABLE public.fases OWNER TO ciecyt_admin;

--
-- Name: ficha_tecnica; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.ficha_tecnica (
    id bigint NOT NULL,
    titulo_profesional character varying(255),
    titulo_postgrado character varying(255),
    experiencia character varying(255),
    ficha_tecnica_user_id bigint
);


ALTER TABLE public.ficha_tecnica OWNER TO ciecyt_admin;

--
-- Name: formato; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.formato (
    id bigint NOT NULL,
    formato character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);


ALTER TABLE public.formato OWNER TO ciecyt_admin;

--
-- Name: grupo_semillero; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.grupo_semillero (
    id bigint NOT NULL,
    nombre character varying(255),
    tipo boolean
);


ALTER TABLE public.grupo_semillero OWNER TO ciecyt_admin;

--
-- Name: impactos_esperados; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.impactos_esperados (
    id bigint NOT NULL,
    impacto character varying(255),
    plazo integer,
    indicador character varying(255),
    supuestos character varying(255),
    impactos_esperado_proyecto_id bigint,
    orden_vista integer
);


ALTER TABLE public.impactos_esperados OWNER TO ciecyt_admin;

--
-- Name: informacion_pasantia; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.informacion_pasantia (
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
    informacion_pasantia_proyecto_id bigint,
    convenio text
);


ALTER TABLE public.informacion_pasantia OWNER TO ciecyt_admin;

--
-- Name: integrante_proyecto; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.integrante_proyecto (
    id bigint NOT NULL,
    integrante character varying(255),
    descripcion character varying(255),
    integrante_proyecto_user_id bigint,
    integrante_proyecto_proyecto_id bigint,
    integrante_proyecto_roles_modalidad_id bigint
);


ALTER TABLE public.integrante_proyecto OWNER TO ciecyt_admin;

--
-- Name: investigacion_tipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.investigacion_tipo (
    id bigint NOT NULL,
    investigacion_tipo text,
    investigacion_tipo_descripcion text,
    tipo text,
    tipo_descripcion text
);


ALTER TABLE public.investigacion_tipo OWNER TO postgres;

--
-- Name: investigacion_tipo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.investigacion_tipo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.investigacion_tipo_id_seq OWNER TO postgres;

--
-- Name: investigacion_tipo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.investigacion_tipo_id_seq OWNED BY public.investigacion_tipo.id;


--
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO ciecyt_admin;

--
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO ciecyt_admin;

--
-- Name: jhi_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_evt_data OWNER TO ciecyt_admin;

--
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.jhi_user (
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


ALTER TABLE public.jhi_user OWNER TO ciecyt_admin;

--
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO ciecyt_admin;

--
-- Name: linea_investigacion; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.linea_investigacion (
    id bigint NOT NULL,
    linea character varying(255),
    codigo_linea character varying(255),
    linea_padre_id bigint,
    linea_investigacion_programa_id bigint
);


ALTER TABLE public.linea_investigacion OWNER TO ciecyt_admin;

--
-- Name: menu; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.menu (
    id bigint NOT NULL,
    nombre character varying(255),
    url character varying(255),
    icono character varying(255),
    activo boolean,
    menu_padre_id bigint,
    es_publico boolean,
    orden integer
);


ALTER TABLE public.menu OWNER TO ciecyt_admin;

--
-- Name: modalidad; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.modalidad (
    id bigint NOT NULL,
    modalidad character varying(255),
    modalidad_acuerdo_id bigint,
    contiene_linea boolean
);


ALTER TABLE public.modalidad OWNER TO ciecyt_admin;

--
-- Name: pregunta; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.pregunta (
    id bigint NOT NULL,
    encabezado character varying(255),
    descripcion text,
    pregunta text,
    pregunta_tipo_pregunta_id bigint,
    pregunta_modalidad_id bigint,
    pregunta_roles_modalidad_id bigint,
    pregunta_elemento text,
    pregunta_elemento_id bigint,
    puntaje double precision,
    puntaje_maximo double precision
);


ALTER TABLE public.pregunta OWNER TO ciecyt_admin;

--
-- Name: presupuesto_valor; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.presupuesto_valor (
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


ALTER TABLE public.presupuesto_valor OWNER TO ciecyt_admin;

--
-- Name: producto; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.producto (
    id bigint NOT NULL,
    producto character varying(255)
);


ALTER TABLE public.producto OWNER TO ciecyt_admin;

--
-- Name: producto_proyecto; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.producto_proyecto (
    id bigint NOT NULL,
    aplica boolean,
    descripcion character varying(255),
    producto_proyecto_producto_id bigint,
    producto_proyecto_proyecto_id bigint
);


ALTER TABLE public.producto_proyecto OWNER TO ciecyt_admin;

--
-- Name: programa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.programa (
    id bigint NOT NULL,
    programa text,
    descripcion text,
    codigo_interno text,
    codigo_snies text,
    creditos integer,
    ciclo text,
    resolucion text,
    titulo text,
    duracion_semestres integer,
    programa_facultad_id bigint
);


ALTER TABLE public.programa OWNER TO postgres;

--
-- Name: programa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.programa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.programa_id_seq OWNER TO postgres;

--
-- Name: programa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id;


--
-- Name: proyecto; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.proyecto (
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
    referencias text,
    proyecto_programa_id bigint,
    programa text,
    departamento character varying(255),
    municipio character varying(255),
    viable boolean,
    enviado boolean,
    fecha_envio_propuesta timestamp with time zone,
    fecha_envio_proyecto timestamp with time zone,
    nota double precision,
    conclusion text,
    recomendaciones text
);


ALTER TABLE public.proyecto OWNER TO ciecyt_admin;

--
-- Name: proyecto_fase; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.proyecto_fase (
    id bigint NOT NULL,
    titulo character varying(255),
    cumplida boolean,
    fecha_cumplimiento date,
    observaciones character varying(255),
    proyecto_fase_fases_id bigint,
    proyecto_fase_proyecto_id bigint
);


ALTER TABLE public.proyecto_fase OWNER TO ciecyt_admin;

--
-- Name: proyecto_respuestas; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.proyecto_respuestas (
    id bigint NOT NULL,
    respuesta character varying(255),
    observaciones character varying(255),
    viable boolean,
    proyecto_respuestas_pregunta_id bigint,
    proyecto_respuestas_proyecto_id bigint,
    proyecto_respuestas_pregunta_pregunta text,
    puntaje double precision,
    si_no boolean
);


ALTER TABLE public.proyecto_respuestas OWNER TO ciecyt_admin;

--
-- Name: resultados_esperados; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.resultados_esperados (
    id bigint NOT NULL,
    resultado character varying(255),
    indicador character varying(255),
    beneficiario character varying(255),
    resultados_esperados_proyecto_id bigint,
    orden_vista integer
);


ALTER TABLE public.resultados_esperados OWNER TO ciecyt_admin;

--
-- Name: retroalimentacion; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.retroalimentacion (
    id bigint NOT NULL,
    titulo character varying(255),
    retroalimentacion character varying(255),
    fecha_retroalimentacion date,
    estado_retroalimentacion date,
    estado_proyecto_fase integer,
    retroalimentacion_proyecto_fase_id bigint,
    retroalimentacion_user_id bigint
);


ALTER TABLE public.retroalimentacion OWNER TO ciecyt_admin;

--
-- Name: rol_menu; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.rol_menu (
    id bigint NOT NULL,
    permitir_acceso boolean,
    permitir_crear boolean,
    permitir_editar boolean,
    permitir_eliminar boolean,
    auth_name character varying(255),
    rol_menu_menu_id bigint
);


ALTER TABLE public.rol_menu OWNER TO ciecyt_admin;

--
-- Name: roles_modalidad; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.roles_modalidad (
    id bigint NOT NULL,
    rol character varying(255),
    cantidad integer,
    calificador boolean,
    roles_modalidad_modalidad_id bigint,
    roles_modalidad_authority_name character varying(60)
);


ALTER TABLE public.roles_modalidad OWNER TO ciecyt_admin;

--
-- Name: rubro; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.rubro (
    id bigint NOT NULL,
    rubro character varying(255)
);


ALTER TABLE public.rubro OWNER TO ciecyt_admin;

--
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: ciecyt_admin
--

CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO ciecyt_admin;

--
-- Name: solicitud; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.solicitud (
    id bigint NOT NULL,
    estado boolean,
    asunto character varying(255),
    texto_solicitud character varying(255),
    fecha_solicitud date,
    solicitud_integrante_proyecto_id bigint
);


ALTER TABLE public.solicitud OWNER TO ciecyt_admin;

--
-- Name: tipo_pregunta; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.tipo_pregunta (
    id bigint NOT NULL,
    tipo_pregunta character varying(255),
    tipo_dato character varying(255)
);


ALTER TABLE public.tipo_pregunta OWNER TO ciecyt_admin;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: ciecyt_admin
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    usuario character varying(255),
    descripcion character varying(255)
);


ALTER TABLE public.usuario OWNER TO ciecyt_admin;

--
-- Name: investigacion_tipo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.investigacion_tipo ALTER COLUMN id SET DEFAULT nextval('public.investigacion_tipo_id_seq'::regclass);


--
-- Name: programa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa ALTER COLUMN id SET DEFAULT nextval('public.programa_id_seq'::regclass);


--
-- Data for Name: acuerdo; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM stdin;
\.
COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM '$$PATH$$/3175.dat';

--
-- Data for Name: adjunto_proyecto_fase; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM stdin;
\.
COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM '$$PATH$$/3176.dat';

--
-- Data for Name: adjunto_retroalimentacion; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM stdin;
\.
COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM '$$PATH$$/3177.dat';

--
-- Data for Name: categorizacion; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM stdin;
\.
COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM '$$PATH$$/3178.dat';

--
-- Data for Name: ciclo_propedeutico; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM stdin;
\.
COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM '$$PATH$$/3179.dat';

--
-- Data for Name: cronograma; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id, orden_vista) FROM stdin;
\.
COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id, orden_vista) FROM '$$PATH$$/3180.dat';

--
-- Data for Name: cronograma_ciecyt; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM stdin;
\.
COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM '$$PATH$$/3181.dat';

--
-- Data for Name: cronograma_ciecyt_fases; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM stdin;
\.
COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM '$$PATH$$/3182.dat';

--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
\.
COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM '$$PATH$$/3183.dat';

--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
\.
COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM '$$PATH$$/3184.dat';

--
-- Data for Name: elemento; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM stdin;
\.
COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM '$$PATH$$/3185.dat';

--
-- Data for Name: elemento_proyecto; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id, elemento_proyecto_proyecto_descripcion) FROM stdin;
\.
COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id, elemento_proyecto_proyecto_descripcion) FROM '$$PATH$$/3186.dat';

--
-- Data for Name: entidad; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.entidad (id, entidad, nit) FROM stdin;
\.
COPY public.entidad (id, entidad, nit) FROM '$$PATH$$/3187.dat';

--
-- Data for Name: entidad_financiadora; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id, entidad_financiadora_proyecto_id) FROM stdin;
\.
COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id, entidad_financiadora_proyecto_id) FROM '$$PATH$$/3188.dat';

--
-- Data for Name: facultad; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.facultad (id, codigo_facultad, facultad) FROM stdin;
\.
COPY public.facultad (id, codigo_facultad, facultad) FROM '$$PATH$$/3189.dat';

--
-- Data for Name: fases; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM stdin;
\.
COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM '$$PATH$$/3190.dat';

--
-- Data for Name: ficha_tecnica; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM stdin;
\.
COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM '$$PATH$$/3191.dat';

--
-- Data for Name: formato; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.formato (id, formato, version, codigo, fecha) FROM stdin;
\.
COPY public.formato (id, formato, version, codigo, fecha) FROM '$$PATH$$/3192.dat';

--
-- Data for Name: grupo_semillero; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.grupo_semillero (id, nombre, tipo) FROM stdin;
\.
COPY public.grupo_semillero (id, nombre, tipo) FROM '$$PATH$$/3193.dat';

--
-- Data for Name: impactos_esperados; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id, orden_vista) FROM stdin;
\.
COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id, orden_vista) FROM '$$PATH$$/3194.dat';

--
-- Data for Name: informacion_pasantia; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.informacion_pasantia (duracion_horas, direccion, email, lunes, martes, miercoles, jueves, viernes, sabado, domingo, bono_alimenticio, apoyo_economico, auxilio_transporte, capacitacion, otro_apoyo, nombre_empresa, nit_empresa, direccion_empresa, sector_economico_empresa, representante_legal_empresa, asesor_empresa, cargo_asesor_empresa, email_asesor_empresa, municipio_empresa, telefono_contacto_empresa, email_empresa, departamento_empresa, identificacion_representante_legal, profesion_asesor_empresa, celular_asesor_empresa, id, informacion_pasantia_proyecto_id, convenio) FROM stdin;
\.
COPY public.informacion_pasantia (duracion_horas, direccion, email, lunes, martes, miercoles, jueves, viernes, sabado, domingo, bono_alimenticio, apoyo_economico, auxilio_transporte, capacitacion, otro_apoyo, nombre_empresa, nit_empresa, direccion_empresa, sector_economico_empresa, representante_legal_empresa, asesor_empresa, cargo_asesor_empresa, email_asesor_empresa, municipio_empresa, telefono_contacto_empresa, email_empresa, departamento_empresa, identificacion_representante_legal, profesion_asesor_empresa, celular_asesor_empresa, id, informacion_pasantia_proyecto_id, convenio) FROM '$$PATH$$/3195.dat';

--
-- Data for Name: integrante_proyecto; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM stdin;
\.
COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM '$$PATH$$/3196.dat';

--
-- Data for Name: investigacion_tipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.investigacion_tipo (id, investigacion_tipo, investigacion_tipo_descripcion, tipo, tipo_descripcion) FROM stdin;
\.
COPY public.investigacion_tipo (id, investigacion_tipo, investigacion_tipo_descripcion, tipo, tipo_descripcion) FROM '$$PATH$$/3222.dat';

--
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.jhi_authority (name) FROM stdin;
\.
COPY public.jhi_authority (name) FROM '$$PATH$$/3197.dat';

--
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
\.
COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM '$$PATH$$/3198.dat';

--
-- Data for Name: jhi_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
\.
COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM '$$PATH$$/3199.dat';

--
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
\.
COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM '$$PATH$$/3200.dat';

--
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
\.
COPY public.jhi_user_authority (user_id, authority_name) FROM '$$PATH$$/3201.dat';

--
-- Data for Name: linea_investigacion; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_programa_id) FROM stdin;
\.
COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_programa_id) FROM '$$PATH$$/3202.dat';

--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM stdin;
\.
COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM '$$PATH$$/3203.dat';

--
-- Data for Name: modalidad; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.modalidad (id, modalidad, modalidad_acuerdo_id, contiene_linea) FROM stdin;
\.
COPY public.modalidad (id, modalidad, modalidad_acuerdo_id, contiene_linea) FROM '$$PATH$$/3204.dat';

--
-- Data for Name: pregunta; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, pregunta_elemento, pregunta_elemento_id, puntaje, puntaje_maximo) FROM stdin;
\.
COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, pregunta_elemento, pregunta_elemento_id, puntaje, puntaje_maximo) FROM '$$PATH$$/3205.dat';

--
-- Data for Name: presupuesto_valor; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id, orden_vista) FROM stdin;
\.
COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id, orden_vista) FROM '$$PATH$$/3206.dat';

--
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.producto (id, producto) FROM stdin;
\.
COPY public.producto (id, producto) FROM '$$PATH$$/3207.dat';

--
-- Data for Name: producto_proyecto; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM stdin;
\.
COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM '$$PATH$$/3208.dat';

--
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.programa (id, programa, descripcion, codigo_interno, codigo_snies, creditos, ciclo, resolucion, titulo, duracion_semestres, programa_facultad_id) FROM stdin;
\.
COPY public.programa (id, programa, descripcion, codigo_interno, codigo_snies, creditos, ciclo, resolucion, titulo, duracion_semestres, programa_facultad_id) FROM '$$PATH$$/3224.dat';

--
-- Data for Name: proyecto; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias, proyecto_programa_id, programa, departamento, municipio, viable, enviado, fecha_envio_propuesta, fecha_envio_proyecto, nota, conclusion, recomendaciones) FROM stdin;
\.
COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias, proyecto_programa_id, programa, departamento, municipio, viable, enviado, fecha_envio_propuesta, fecha_envio_proyecto, nota, conclusion, recomendaciones) FROM '$$PATH$$/3209.dat';

--
-- Data for Name: proyecto_fase; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM stdin;
\.
COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM '$$PATH$$/3210.dat';

--
-- Data for Name: proyecto_respuestas; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id, proyecto_respuestas_pregunta_pregunta, puntaje, si_no) FROM stdin;
\.
COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id, proyecto_respuestas_pregunta_pregunta, puntaje, si_no) FROM '$$PATH$$/3211.dat';

--
-- Data for Name: resultados_esperados; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id, orden_vista) FROM stdin;
\.
COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id, orden_vista) FROM '$$PATH$$/3212.dat';

--
-- Data for Name: retroalimentacion; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM stdin;
\.
COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM '$$PATH$$/3213.dat';

--
-- Data for Name: rol_menu; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM stdin;
\.
COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM '$$PATH$$/3214.dat';

--
-- Data for Name: roles_modalidad; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id, roles_modalidad_authority_name) FROM stdin;
\.
COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id, roles_modalidad_authority_name) FROM '$$PATH$$/3215.dat';

--
-- Data for Name: rubro; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.rubro (id, rubro) FROM stdin;
\.
COPY public.rubro (id, rubro) FROM '$$PATH$$/3216.dat';

--
-- Data for Name: solicitud; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM stdin;
\.
COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM '$$PATH$$/3218.dat';

--
-- Data for Name: tipo_pregunta; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM stdin;
\.
COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM '$$PATH$$/3219.dat';

--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: ciecyt_admin
--

COPY public.usuario (id, usuario, descripcion) FROM stdin;
\.
COPY public.usuario (id, usuario, descripcion) FROM '$$PATH$$/3220.dat';

--
-- Name: investigacion_tipo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.investigacion_tipo_id_seq', 1, true);


--
-- Name: programa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_id_seq', 1, true);


--
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: ciecyt_admin
--

SELECT pg_catalog.setval('public.sequence_generator', 31450, true);


--
-- Name: acuerdo acuerdo_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.acuerdo
    ADD CONSTRAINT acuerdo_pkey PRIMARY KEY (id);


--
-- Name: adjunto_proyecto_fase adjunto_proyecto_fase_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_pkey PRIMARY KEY (id);


--
-- Name: adjunto_retroalimentacion adjunto_retroalimentacion_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_pkey PRIMARY KEY (id);


--
-- Name: categorizacion categorizacion_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT categorizacion_pkey PRIMARY KEY (id);


--
-- Name: ciclo_propedeutico ciclo_propedeutico_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT ciclo_propedeutico_pkey PRIMARY KEY (id);


--
-- Name: cronograma_ciecyt_fases cronograma_ciecyt_fases_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_pkey PRIMARY KEY (id);


--
-- Name: cronograma_ciecyt cronograma_ciecyt_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT cronograma_ciecyt_pkey PRIMARY KEY (id);


--
-- Name: cronograma cronograma_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT cronograma_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: elemento elemento_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT elemento_pkey PRIMARY KEY (id);


--
-- Name: elemento_proyecto elemento_proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT elemento_proyecto_pkey PRIMARY KEY (id);


--
-- Name: entidad_financiadora entidad_financiadora_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT entidad_financiadora_pkey PRIMARY KEY (id);


--
-- Name: entidad entidad_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.entidad
    ADD CONSTRAINT entidad_pkey PRIMARY KEY (id);


--
-- Name: facultad facultad_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id);


--
-- Name: fases fases_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fases_pkey PRIMARY KEY (id);


--
-- Name: ficha_tecnica ficha_tecnica_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT ficha_tecnica_pkey PRIMARY KEY (id);


--
-- Name: formato formato_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);


--
-- Name: grupo_semillero grupo_semillero_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.grupo_semillero
    ADD CONSTRAINT grupo_semillero_pkey PRIMARY KEY (id);


--
-- Name: impactos_esperados impactos_esperados_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT impactos_esperados_pkey PRIMARY KEY (id);


--
-- Name: informacion_pasantia informacion_pasantia_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.informacion_pasantia
    ADD CONSTRAINT informacion_pasantia_pkey PRIMARY KEY (id);


--
-- Name: integrante_proyecto integrante_proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT integrante_proyecto_pkey PRIMARY KEY (id);


--
-- Name: investigacion_tipo investigacion_tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.investigacion_tipo
    ADD CONSTRAINT investigacion_tipo_pkey PRIMARY KEY (id);


--
-- Name: jhi_authority jhi_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);


--
-- Name: jhi_persistent_audit_event jhi_persistent_audit_event_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);


--
-- Name: jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: jhi_user_authority jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: jhi_user jhi_user_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);


--
-- Name: linea_investigacion linea_investigacion_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT linea_investigacion_pkey PRIMARY KEY (id);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- Name: modalidad modalidad_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT modalidad_pkey PRIMARY KEY (id);


--
-- Name: pregunta pregunta_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);


--
-- Name: presupuesto_valor presupuesto_valor_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT presupuesto_valor_pkey PRIMARY KEY (id);


--
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);


--
-- Name: producto_proyecto producto_proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT producto_proyecto_pkey PRIMARY KEY (id);


--
-- Name: programa programa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id);


--
-- Name: proyecto_fase proyecto_fase_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT proyecto_fase_pkey PRIMARY KEY (id);


--
-- Name: proyecto proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);


--
-- Name: proyecto_respuestas proyecto_respuestas_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT proyecto_respuestas_pkey PRIMARY KEY (id);


--
-- Name: resultados_esperados resultados_esperados_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT resultados_esperados_pkey PRIMARY KEY (id);


--
-- Name: retroalimentacion retroalimentacion_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT retroalimentacion_pkey PRIMARY KEY (id);


--
-- Name: rol_menu rol_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT rol_menu_pkey PRIMARY KEY (id);


--
-- Name: roles_modalidad roles_modalidad_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT roles_modalidad_pkey PRIMARY KEY (id);


--
-- Name: rubro rubro_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.rubro
    ADD CONSTRAINT rubro_pkey PRIMARY KEY (id);


--
-- Name: solicitud solicitud_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);


--
-- Name: tipo_pregunta tipo_pregunta_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: jhi_user ux_user_email; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: jhi_user ux_user_login; Type: CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: fki_fk_linea_investigacion_linea_investigacion_programa_id; Type: INDEX; Schema: public; Owner: ciecyt_admin
--

CREATE INDEX fki_fk_linea_investigacion_linea_investigacion_programa_id ON public.linea_investigacion USING btree (linea_investigacion_programa_id);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: ciecyt_admin
--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: ciecyt_admin
--

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);


--
-- Name: adjunto_proyecto_fase adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id FOREIGN KEY (adjunto_proyecto_fase_proyecto_fase_id) REFERENCES public.proyecto_fase(id);


--
-- Name: adjunto_retroalimentacion adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id FOREIGN KEY (adjunto_retroalimentacion_retroalimentacion_id) REFERENCES public.retroalimentacion(id);


--
-- Name: cronograma_ciecyt_fases cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id FOREIGN KEY (cronograma_ciecyt_fases_cronograma_ciecyt_id) REFERENCES public.cronograma_ciecyt(id);


--
-- Name: jhi_user_authority fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--
-- Name: categorizacion fk_categorizacion_categorizacion_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT fk_categorizacion_categorizacion_proyecto_id FOREIGN KEY (categorizacion_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: ciclo_propedeutico fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id FOREIGN KEY (ciclo_propedeutico_acuerdo_id) REFERENCES public.acuerdo(id);


--
-- Name: ciclo_propedeutico fk_ciclo_propedeutico_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_modalidad_id FOREIGN KEY (modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: cronograma_ciecyt fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id FOREIGN KEY (cronograma_ciecyt_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: cronograma_ciecyt_fases fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id FOREIGN KEY (cronograma_ciecyt_fases_fases_id) REFERENCES public.fases(id);


--
-- Name: cronograma fk_cronograma_cronograma_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT fk_cronograma_cronograma_proyecto_id FOREIGN KEY (cronograma_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: elemento fk_elemento_elemento_formato_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_formato_id FOREIGN KEY (elemento_formato_id) REFERENCES public.formato(id);


--
-- Name: elemento fk_elemento_elemento_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_modalidad_id FOREIGN KEY (elemento_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: elemento_proyecto fk_elemento_proyecto_elemento_proyecto_elemento_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id FOREIGN KEY (elemento_proyecto_elemento_id) REFERENCES public.elemento(id);


--
-- Name: elemento_proyecto fk_elemento_proyecto_elemento_proyecto_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id FOREIGN KEY (elemento_proyecto_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: entidad_financiadora fk_entidad_financiadora_entidad_financiadora_entidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id FOREIGN KEY (entidad_financiadora_entidad_id) REFERENCES public.entidad(id);


--
-- Name: jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--
-- Name: fases fk_fases_fases_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fk_fases_fases_modalidad_id FOREIGN KEY (fases_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: ficha_tecnica fk_ficha_tecnica_ficha_tecnica_user_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id FOREIGN KEY (ficha_tecnica_user_id) REFERENCES public.jhi_user(id);


--
-- Name: impactos_esperados fk_impactos_esperados_impactos_esperado_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id FOREIGN KEY (impactos_esperado_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: integrante_proyecto fk_integrante_proyecto_integrante_proyecto_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id FOREIGN KEY (integrante_proyecto_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: integrante_proyecto fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id FOREIGN KEY (integrante_proyecto_roles_modalidad_id) REFERENCES public.roles_modalidad(id);


--
-- Name: integrante_proyecto fk_integrante_proyecto_integrante_proyecto_user_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id FOREIGN KEY (integrante_proyecto_user_id) REFERENCES public.jhi_user(id);


--
-- Name: linea_investigacion fk_linea_investigacion_linea_investigacion_programa_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_investigacion_programa_id FOREIGN KEY (linea_investigacion_programa_id) REFERENCES public.programa(id) NOT VALID;


--
-- Name: linea_investigacion fk_linea_investigacion_linea_padre_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_padre_id FOREIGN KEY (linea_padre_id) REFERENCES public.linea_investigacion(id);


--
-- Name: menu fk_menu_menu_padre_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fk_menu_menu_padre_id FOREIGN KEY (menu_padre_id) REFERENCES public.menu(id);


--
-- Name: modalidad fk_modalidad_modalidad_acuerdo_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT fk_modalidad_modalidad_acuerdo_id FOREIGN KEY (modalidad_acuerdo_id) REFERENCES public.acuerdo(id);


--
-- Name: pregunta fk_pregunta_pregunta_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: pregunta fk_pregunta_pregunta_roles_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);


--
-- Name: pregunta fk_pregunta_pregunta_tipo_pregunta_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);


--
-- Name: presupuesto_valor fk_presupuesto_valor_presupuesto_valor_entidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id FOREIGN KEY (presupuesto_valor_entidad_id) REFERENCES public.entidad(id);


--
-- Name: presupuesto_valor fk_presupuesto_valor_presupuesto_valor_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id FOREIGN KEY (presupuesto_valor_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: presupuesto_valor fk_presupuesto_valor_presupuesto_valor_rubro_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id FOREIGN KEY (presupuesto_valor_rubro_id) REFERENCES public.rubro(id);


--
-- Name: producto_proyecto fk_producto_proyecto_producto_proyecto_producto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id FOREIGN KEY (producto_proyecto_producto_id) REFERENCES public.producto(id);


--
-- Name: producto_proyecto fk_producto_proyecto_producto_proyecto_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id FOREIGN KEY (producto_proyecto_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: proyecto fk_proyecto_facultad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);


--
-- Name: proyecto_fase fk_proyecto_fase_proyecto_fase_fases_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id FOREIGN KEY (proyecto_fase_fases_id) REFERENCES public.fases(id);


--
-- Name: proyecto_fase fk_proyecto_fase_proyecto_fase_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id FOREIGN KEY (proyecto_fase_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: proyecto fk_proyecto_proyecto_grupo_semillero_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);


--
-- Name: proyecto fk_proyecto_proyecto_linea_investigacion_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);


--
-- Name: proyecto fk_proyecto_proyecto_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_pregunta_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id FOREIGN KEY (proyecto_respuestas_pregunta_id) REFERENCES public.pregunta(id);


--
-- Name: proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id FOREIGN KEY (proyecto_respuestas_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: resultados_esperados fk_resultados_esperados_resultados_esperados_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id FOREIGN KEY (resultados_esperados_proyecto_id) REFERENCES public.proyecto(id);


--
-- Name: retroalimentacion fk_retroalimentacion_retroalimentacion_proyecto_fase_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id FOREIGN KEY (retroalimentacion_proyecto_fase_id) REFERENCES public.proyecto_fase(id);


--
-- Name: retroalimentacion fk_retroalimentacion_retroalimentacion_user_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id FOREIGN KEY (retroalimentacion_user_id) REFERENCES public.jhi_user(id);


--
-- Name: rol_menu fk_rol_menu_rol_menu_menu_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT fk_rol_menu_rol_menu_menu_id FOREIGN KEY (rol_menu_menu_id) REFERENCES public.menu(id);


--
-- Name: roles_modalidad fk_roles_modalidad_roles_modalidad_modalidad_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id FOREIGN KEY (roles_modalidad_modalidad_id) REFERENCES public.modalidad(id);


--
-- Name: solicitud fk_solicitud_solicitud_integrante_proyecto_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id FOREIGN KEY (solicitud_integrante_proyecto_id) REFERENCES public.integrante_proyecto(id);


--
-- Name: jhi_user_authority fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: ciecyt_admin
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           