PGDMP                     	    x         	   ciecyt_db    11.6    11.6 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    52041 	   ciecyt_db    DATABASE     �   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';
    DROP DATABASE ciecyt_db;
             postgres    false            �            1259    52042    acuerdo    TABLE     �   CREATE TABLE public.acuerdo (
    id bigint NOT NULL,
    acuerdo character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.acuerdo;
       public         ciecyt_admin    false            �            1259    52048    adjunto_proyecto_fase    TABLE     �  CREATE TABLE public.adjunto_proyecto_fase (
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
       public         ciecyt_admin    false            �            1259    52054    adjunto_retroalimentacion    TABLE     �  CREATE TABLE public.adjunto_retroalimentacion (
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
       public         ciecyt_admin    false            �            1259    52060    categorizacion    TABLE     �   CREATE TABLE public.categorizacion (
    id bigint NOT NULL,
    categoria character varying(255),
    descripcion character varying(255),
    categorizacion_proyecto_id bigint
);
 "   DROP TABLE public.categorizacion;
       public         ciecyt_admin    false            �            1259    52066    ciclo_propedeutico    TABLE     �   CREATE TABLE public.ciclo_propedeutico (
    id bigint NOT NULL,
    ciclo character varying(255),
    modalidad_id bigint,
    ciclo_propedeutico_acuerdo_id bigint
);
 &   DROP TABLE public.ciclo_propedeutico;
       public         ciecyt_admin    false            �            1259    52069 
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
       public         ciecyt_admin    false            �            1259    52072    cronograma_ciecyt    TABLE     �   CREATE TABLE public.cronograma_ciecyt (
    id bigint NOT NULL,
    titulo_cronograma character varying(255),
    fecha_inicio date,
    fecha_fin date,
    observaciones character varying(255),
    cronograma_ciecyt_modalidad_id bigint
);
 %   DROP TABLE public.cronograma_ciecyt;
       public         ciecyt_admin    false            �            1259    52078    cronograma_ciecyt_fases    TABLE       CREATE TABLE public.cronograma_ciecyt_fases (
    id bigint NOT NULL,
    inicio_fase date,
    fin_fase date,
    texto_explicativo character varying(255),
    cronograma_ciecyt_fases_cronograma_ciecyt_id bigint,
    cronograma_ciecyt_fases_fases_id bigint
);
 +   DROP TABLE public.cronograma_ciecyt_fases;
       public         ciecyt_admin    false            �            1259    52081    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
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
       public         ciecyt_admin    false            �            1259    52087    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public         ciecyt_admin    false            �            1259    52090    elemento    TABLE     �   CREATE TABLE public.elemento (
    id bigint NOT NULL,
    elemento character varying(255),
    elemento_formato_id bigint,
    elemento_modalidad_id bigint,
    descripcion text
);
    DROP TABLE public.elemento;
       public         ciecyt_admin    false            �            1259    52096    elemento_proyecto    TABLE     �   CREATE TABLE public.elemento_proyecto (
    id bigint NOT NULL,
    dato text,
    elemento_proyecto_elemento_id bigint,
    elemento_proyecto_proyecto_id bigint,
    elemento_proyecto_proyecto_descripcion text
);
 %   DROP TABLE public.elemento_proyecto;
       public         ciecyt_admin    false            �            1259    52102    entidad    TABLE     |   CREATE TABLE public.entidad (
    id bigint NOT NULL,
    entidad character varying(255),
    nit character varying(255)
);
    DROP TABLE public.entidad;
       public         ciecyt_admin    false            �            1259    52108    entidad_financiadora    TABLE     �   CREATE TABLE public.entidad_financiadora (
    id bigint NOT NULL,
    valor double precision,
    aprobada boolean,
    entidad_financiadora_entidad_id bigint,
    entidad_financiadora_proyecto_id bigint
);
 (   DROP TABLE public.entidad_financiadora;
       public         ciecyt_admin    false            �            1259    52111    facultad    TABLE     �   CREATE TABLE public.facultad (
    id bigint NOT NULL,
    codigo_facultad character varying(255),
    facultad character varying(255)
);
    DROP TABLE public.facultad;
       public         ciecyt_admin    false            �            1259    52117    fases    TABLE     �   CREATE TABLE public.fases (
    id bigint NOT NULL,
    fase character varying(255),
    notificable boolean,
    fases_modalidad_id bigint
);
    DROP TABLE public.fases;
       public         ciecyt_admin    false            �            1259    52120    ficha_tecnica    TABLE     �   CREATE TABLE public.ficha_tecnica (
    id bigint NOT NULL,
    titulo_profesional character varying(255),
    titulo_postgrado character varying(255),
    experiencia character varying(255),
    ficha_tecnica_user_id bigint
);
 !   DROP TABLE public.ficha_tecnica;
       public         ciecyt_admin    false            �            1259    52126    formato    TABLE     �   CREATE TABLE public.formato (
    id bigint NOT NULL,
    formato character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.formato;
       public         ciecyt_admin    false            �            1259    52132    grupo_semillero    TABLE     u   CREATE TABLE public.grupo_semillero (
    id bigint NOT NULL,
    nombre character varying(255),
    tipo boolean
);
 #   DROP TABLE public.grupo_semillero;
       public         ciecyt_admin    false            �            1259    52135    impactos_esperados    TABLE     	  CREATE TABLE public.impactos_esperados (
    id bigint NOT NULL,
    impacto character varying(255),
    plazo integer,
    indicador character varying(255),
    supuestos character varying(255),
    impactos_esperado_proyecto_id bigint,
    orden_vista integer
);
 &   DROP TABLE public.impactos_esperados;
       public         ciecyt_admin    false            �            1259    52141    informacion_pasantia    TABLE     �  CREATE TABLE public.informacion_pasantia (
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
       public         ciecyt_admin    false            �            1259    52147    integrante_proyecto    TABLE       CREATE TABLE public.integrante_proyecto (
    id bigint NOT NULL,
    integrante character varying(255),
    descripcion character varying(255),
    integrante_proyecto_user_id bigint,
    integrante_proyecto_proyecto_id bigint,
    integrante_proyecto_roles_modalidad_id bigint
);
 '   DROP TABLE public.integrante_proyecto;
       public         ciecyt_admin    false            �            1259    60801    investigacion_tipo    TABLE     �   CREATE TABLE public.investigacion_tipo (
    id bigint NOT NULL,
    investigacion_tipo text,
    investigacion_tipo_descripcion text,
    tipo text,
    tipo_descripcion text
);
 &   DROP TABLE public.investigacion_tipo;
       public         postgres    false            �            1259    60799    investigacion_tipo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.investigacion_tipo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.investigacion_tipo_id_seq;
       public       postgres    false    243            �           0    0    investigacion_tipo_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.investigacion_tipo_id_seq OWNED BY public.investigacion_tipo.id;
            public       postgres    false    242            �            1259    52153    jhi_authority    TABLE     O   CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);
 !   DROP TABLE public.jhi_authority;
       public         ciecyt_admin    false            �            1259    52156    jhi_persistent_audit_event    TABLE     �   CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);
 .   DROP TABLE public.jhi_persistent_audit_event;
       public         ciecyt_admin    false            �            1259    52159    jhi_persistent_audit_evt_data    TABLE     �   CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);
 1   DROP TABLE public.jhi_persistent_audit_evt_data;
       public         ciecyt_admin    false            �            1259    52162    jhi_user    TABLE     �  CREATE TABLE public.jhi_user (
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
       public         ciecyt_admin    false            �            1259    52168    jhi_user_authority    TABLE     {   CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);
 &   DROP TABLE public.jhi_user_authority;
       public         ciecyt_admin    false            �            1259    52171    linea_investigacion    TABLE     �   CREATE TABLE public.linea_investigacion (
    id bigint NOT NULL,
    linea character varying(255),
    codigo_linea character varying(255),
    linea_padre_id bigint,
    linea_investigacion_programa_id bigint
);
 '   DROP TABLE public.linea_investigacion;
       public         ciecyt_admin    false            �            1259    52177    menu    TABLE     �   CREATE TABLE public.menu (
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
       public         ciecyt_admin    false            �            1259    52183 	   modalidad    TABLE     �   CREATE TABLE public.modalidad (
    id bigint NOT NULL,
    modalidad character varying(255),
    modalidad_acuerdo_id bigint
);
    DROP TABLE public.modalidad;
       public         ciecyt_admin    false            �            1259    52186    pregunta    TABLE     $  CREATE TABLE public.pregunta (
    id bigint NOT NULL,
    encabezado character varying(255),
    descripcion text,
    pregunta text,
    pregunta_tipo_pregunta_id bigint,
    pregunta_modalidad_id bigint,
    pregunta_roles_modalidad_id bigint,
    elemento text,
    elemento_id bigint
);
    DROP TABLE public.pregunta;
       public         ciecyt_admin    false            �            1259    52192    presupuesto_valor    TABLE     �  CREATE TABLE public.presupuesto_valor (
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
       public         ciecyt_admin    false            �            1259    52198    producto    TABLE     ^   CREATE TABLE public.producto (
    id bigint NOT NULL,
    producto character varying(255)
);
    DROP TABLE public.producto;
       public         ciecyt_admin    false            �            1259    52201    producto_proyecto    TABLE     �   CREATE TABLE public.producto_proyecto (
    id bigint NOT NULL,
    aplica boolean,
    descripcion character varying(255),
    producto_proyecto_producto_id bigint,
    producto_proyecto_proyecto_id bigint
);
 %   DROP TABLE public.producto_proyecto;
       public         ciecyt_admin    false            �            1259    60813    programa    TABLE       CREATE TABLE public.programa (
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
       public         postgres    false            �            1259    60811    programa_id_seq    SEQUENCE     x   CREATE SEQUENCE public.programa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.programa_id_seq;
       public       postgres    false    245            �           0    0    programa_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id;
            public       postgres    false    244            �            1259    52204    proyecto    TABLE     �  CREATE TABLE public.proyecto (
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
    programa text
);
    DROP TABLE public.proyecto;
       public         ciecyt_admin    false            �            1259    52210    proyecto_fase    TABLE       CREATE TABLE public.proyecto_fase (
    id bigint NOT NULL,
    titulo character varying(255),
    cumplida boolean,
    fecha_cumplimiento date,
    observaciones character varying(255),
    proyecto_fase_fases_id bigint,
    proyecto_fase_proyecto_id bigint
);
 !   DROP TABLE public.proyecto_fase;
       public         ciecyt_admin    false            �            1259    52216    proyecto_respuestas    TABLE     0  CREATE TABLE public.proyecto_respuestas (
    id bigint NOT NULL,
    respuesta character varying(255),
    observaciones character varying(255),
    viable boolean,
    proyecto_respuestas_pregunta_id bigint,
    proyecto_respuestas_proyecto_id bigint,
    proyecto_respuestas_pregunta_pregunta text
);
 '   DROP TABLE public.proyecto_respuestas;
       public         ciecyt_admin    false            �            1259    52222    resultados_esperados    TABLE        CREATE TABLE public.resultados_esperados (
    id bigint NOT NULL,
    resultado character varying(255),
    indicador character varying(255),
    beneficiario character varying(255),
    resultados_esperados_proyecto_id bigint,
    orden_vista integer
);
 (   DROP TABLE public.resultados_esperados;
       public         ciecyt_admin    false            �            1259    52228    retroalimentacion    TABLE     O  CREATE TABLE public.retroalimentacion (
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
       public         ciecyt_admin    false            �            1259    52234    rol_menu    TABLE     �   CREATE TABLE public.rol_menu (
    id bigint NOT NULL,
    permitir_acceso boolean,
    permitir_crear boolean,
    permitir_editar boolean,
    permitir_eliminar boolean,
    auth_name character varying(255),
    rol_menu_menu_id bigint
);
    DROP TABLE public.rol_menu;
       public         ciecyt_admin    false            �            1259    52237    roles_modalidad    TABLE     �   CREATE TABLE public.roles_modalidad (
    id bigint NOT NULL,
    rol character varying(255),
    cantidad integer,
    calificador boolean,
    roles_modalidad_modalidad_id bigint,
    roles_modalidad_authority_name character varying(60)
);
 #   DROP TABLE public.roles_modalidad;
       public         ciecyt_admin    false            �            1259    52240    rubro    TABLE     X   CREATE TABLE public.rubro (
    id bigint NOT NULL,
    rubro character varying(255)
);
    DROP TABLE public.rubro;
       public         ciecyt_admin    false            �            1259    52243    sequence_generator    SEQUENCE        CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sequence_generator;
       public       ciecyt_admin    false            �            1259    52245 	   solicitud    TABLE     �   CREATE TABLE public.solicitud (
    id bigint NOT NULL,
    estado boolean,
    asunto character varying(255),
    texto_solicitud character varying(255),
    fecha_solicitud date,
    solicitud_integrante_proyecto_id bigint
);
    DROP TABLE public.solicitud;
       public         ciecyt_admin    false            �            1259    52251    tipo_pregunta    TABLE     �   CREATE TABLE public.tipo_pregunta (
    id bigint NOT NULL,
    tipo_pregunta character varying(255),
    tipo_dato character varying(255)
);
 !   DROP TABLE public.tipo_pregunta;
       public         ciecyt_admin    false            �            1259    52257    usuario    TABLE     �   CREATE TABLE public.usuario (
    id bigint NOT NULL,
    usuario character varying(255),
    descripcion character varying(255)
);
    DROP TABLE public.usuario;
       public         ciecyt_admin    false            X           2604    60804    investigacion_tipo id    DEFAULT     ~   ALTER TABLE ONLY public.investigacion_tipo ALTER COLUMN id SET DEFAULT nextval('public.investigacion_tipo_id_seq'::regclass);
 D   ALTER TABLE public.investigacion_tipo ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    243    242    243            Y           2604    60816    programa id    DEFAULT     j   ALTER TABLE ONLY public.programa ALTER COLUMN id SET DEFAULT nextval('public.programa_id_seq'::regclass);
 :   ALTER TABLE public.programa ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    244    245    245            g          0    52042    acuerdo 
   TABLE DATA               F   COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    196    2      h          0    52048    adjunto_proyecto_fase 
   TABLE DATA               �   COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM stdin;
    public       ciecyt_admin    false    197   h2      i          0    52054    adjunto_retroalimentacion 
   TABLE DATA               �   COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM stdin;
    public       ciecyt_admin    false    198   v4      j          0    52060    categorizacion 
   TABLE DATA               `   COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    199   �6      k          0    52066    ciclo_propedeutico 
   TABLE DATA               d   COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM stdin;
    public       ciecyt_admin    false    200   �7      l          0    52069 
   cronograma 
   TABLE DATA               {   COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    201   �7      m          0    52072    cronograma_ciecyt 
   TABLE DATA               �   COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    202   �8      n          0    52078    cronograma_ciecyt_fases 
   TABLE DATA               �   COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM stdin;
    public       ciecyt_admin    false    203   :      o          0    52081    databasechangelog 
   TABLE DATA               �   COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    public       ciecyt_admin    false    204   �:      p          0    52087    databasechangeloglock 
   TABLE DATA               R   COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    public       ciecyt_admin    false    205   kJ      q          0    52090    elemento 
   TABLE DATA               i   COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM stdin;
    public       ciecyt_admin    false    206   �J      r          0    52096    elemento_proyecto 
   TABLE DATA               �   COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id, elemento_proyecto_proyecto_descripcion) FROM stdin;
    public       ciecyt_admin    false    207   bZ      s          0    52102    entidad 
   TABLE DATA               3   COPY public.entidad (id, entidad, nit) FROM stdin;
    public       ciecyt_admin    false    208   m�      t          0    52108    entidad_financiadora 
   TABLE DATA               �   COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id, entidad_financiadora_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    209   ��      u          0    52111    facultad 
   TABLE DATA               A   COPY public.facultad (id, codigo_facultad, facultad) FROM stdin;
    public       ciecyt_admin    false    210   �      v          0    52117    fases 
   TABLE DATA               J   COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    211   ��      w          0    52120    ficha_tecnica 
   TABLE DATA               u   COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM stdin;
    public       ciecyt_admin    false    212   z�      x          0    52126    formato 
   TABLE DATA               F   COPY public.formato (id, formato, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    213   n�      y          0    52132    grupo_semillero 
   TABLE DATA               ;   COPY public.grupo_semillero (id, nombre, tipo) FROM stdin;
    public       ciecyt_admin    false    214   ��      z          0    52135    impactos_esperados 
   TABLE DATA               �   COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    215   ��      {          0    52141    informacion_pasantia 
   TABLE DATA               Y  COPY public.informacion_pasantia (duracion_horas, direccion, email, lunes, martes, miercoles, jueves, viernes, sabado, domingo, bono_alimenticio, apoyo_economico, auxilio_transporte, capacitacion, otro_apoyo, nombre_empresa, nit_empresa, direccion_empresa, sector_economico_empresa, representante_legal_empresa, asesor_empresa, cargo_asesor_empresa, email_asesor_empresa, municipio_empresa, telefono_contacto_empresa, email_empresa, departamento_empresa, identificacion_representante_legal, profesion_asesor_empresa, celular_asesor_empresa, id, informacion_pasantia_proyecto_id, convenio) FROM stdin;
    public       ciecyt_admin    false    216   I�      |          0    52147    integrante_proyecto 
   TABLE DATA               �   COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    217   Љ      �          0    60801    investigacion_tipo 
   TABLE DATA               |   COPY public.investigacion_tipo (id, investigacion_tipo, investigacion_tipo_descripcion, tipo, tipo_descripcion) FROM stdin;
    public       postgres    false    243   ��      }          0    52153    jhi_authority 
   TABLE DATA               -   COPY public.jhi_authority (name) FROM stdin;
    public       ciecyt_admin    false    218   �      ~          0    52156    jhi_persistent_audit_event 
   TABLE DATA               a   COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
    public       ciecyt_admin    false    219   j�                0    52159    jhi_persistent_audit_evt_data 
   TABLE DATA               N   COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
    public       ciecyt_admin    false    220   ��      �          0    52162    jhi_user 
   TABLE DATA               �   COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
    public       ciecyt_admin    false    221   ��      �          0    52168    jhi_user_authority 
   TABLE DATA               E   COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
    public       ciecyt_admin    false    222   �      �          0    52171    linea_investigacion 
   TABLE DATA               w   COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_programa_id) FROM stdin;
    public       ciecyt_admin    false    223   ��      �          0    52177    menu 
   TABLE DATA               `   COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM stdin;
    public       ciecyt_admin    false    224   ޙ      �          0    52183 	   modalidad 
   TABLE DATA               H   COPY public.modalidad (id, modalidad, modalidad_acuerdo_id) FROM stdin;
    public       ciecyt_admin    false    225   ��      �          0    52186    pregunta 
   TABLE DATA               �   COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, elemento, elemento_id) FROM stdin;
    public       ciecyt_admin    false    226   `�      �          0    52192    presupuesto_valor 
   TABLE DATA               �   COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    227   ��      �          0    52198    producto 
   TABLE DATA               0   COPY public.producto (id, producto) FROM stdin;
    public       ciecyt_admin    false    228   ޡ      �          0    52201    producto_proyecto 
   TABLE DATA               �   COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    229   ��      �          0    60813    programa 
   TABLE DATA               �   COPY public.programa (id, programa, descripcion, codigo_interno, codigo_snies, creditos, ciclo, resolucion, titulo, duracion_semestres, programa_facultad_id) FROM stdin;
    public       postgres    false    245   t�      �          0    52204    proyecto 
   TABLE DATA               e  COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias, proyecto_programa_id, programa) FROM stdin;
    public       ciecyt_admin    false    230   @�      �          0    52210    proyecto_fase 
   TABLE DATA               �   COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    231   ��      �          0    52216    proyecto_respuestas 
   TABLE DATA               �   COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id, proyecto_respuestas_pregunta_pregunta) FROM stdin;
    public       ciecyt_admin    false    232   �      �          0    52222    resultados_esperados 
   TABLE DATA               �   COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id, orden_vista) FROM stdin;
    public       ciecyt_admin    false    233   W�      �          0    52228    retroalimentacion 
   TABLE DATA               �   COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM stdin;
    public       ciecyt_admin    false    234   ��      �          0    52234    rol_menu 
   TABLE DATA               �   COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM stdin;
    public       ciecyt_admin    false    235   8�      �          0    52237    roles_modalidad 
   TABLE DATA               �   COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id, roles_modalidad_authority_name) FROM stdin;
    public       ciecyt_admin    false    236   ��      �          0    52240    rubro 
   TABLE DATA               *   COPY public.rubro (id, rubro) FROM stdin;
    public       ciecyt_admin    false    237   o�      �          0    52245 	   solicitud 
   TABLE DATA               {   COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    239   0�      �          0    52251    tipo_pregunta 
   TABLE DATA               E   COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM stdin;
    public       ciecyt_admin    false    240   ��      �          0    52257    usuario 
   TABLE DATA               ;   COPY public.usuario (id, usuario, descripcion) FROM stdin;
    public       ciecyt_admin    false    241   �      �           0    0    investigacion_tipo_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.investigacion_tipo_id_seq', 1, true);
            public       postgres    false    242            �           0    0    programa_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.programa_id_seq', 1, true);
            public       postgres    false    244            �           0    0    sequence_generator    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sequence_generator', 28450, true);
            public       ciecyt_admin    false    238            [           2606    52265    acuerdo acuerdo_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.acuerdo
    ADD CONSTRAINT acuerdo_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.acuerdo DROP CONSTRAINT acuerdo_pkey;
       public         ciecyt_admin    false    196            ]           2606    52267 0   adjunto_proyecto_fase adjunto_proyecto_fase_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_pkey;
       public         ciecyt_admin    false    197            _           2606    52269 8   adjunto_retroalimentacion adjunto_retroalimentacion_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_pkey;
       public         ciecyt_admin    false    198            a           2606    52271 "   categorizacion categorizacion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT categorizacion_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT categorizacion_pkey;
       public         ciecyt_admin    false    199            c           2606    52273 *   ciclo_propedeutico ciclo_propedeutico_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT ciclo_propedeutico_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT ciclo_propedeutico_pkey;
       public         ciecyt_admin    false    200            i           2606    52275 4   cronograma_ciecyt_fases cronograma_ciecyt_fases_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_pkey;
       public         ciecyt_admin    false    203            g           2606    52277 (   cronograma_ciecyt cronograma_ciecyt_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT cronograma_ciecyt_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT cronograma_ciecyt_pkey;
       public         ciecyt_admin    false    202            e           2606    52279    cronograma cronograma_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT cronograma_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT cronograma_pkey;
       public         ciecyt_admin    false    201            k           2606    52281 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public         ciecyt_admin    false    205            m           2606    52283    elemento elemento_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT elemento_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.elemento DROP CONSTRAINT elemento_pkey;
       public         ciecyt_admin    false    206            o           2606    52285 (   elemento_proyecto elemento_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT elemento_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT elemento_proyecto_pkey;
       public         ciecyt_admin    false    207            s           2606    52287 .   entidad_financiadora entidad_financiadora_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT entidad_financiadora_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT entidad_financiadora_pkey;
       public         ciecyt_admin    false    209            q           2606    52289    entidad entidad_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.entidad
    ADD CONSTRAINT entidad_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.entidad DROP CONSTRAINT entidad_pkey;
       public         ciecyt_admin    false    208            u           2606    52291    facultad facultad_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.facultad DROP CONSTRAINT facultad_pkey;
       public         ciecyt_admin    false    210            w           2606    52293    fases fases_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fases_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.fases DROP CONSTRAINT fases_pkey;
       public         ciecyt_admin    false    211            y           2606    52295     ficha_tecnica ficha_tecnica_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT ficha_tecnica_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT ficha_tecnica_pkey;
       public         ciecyt_admin    false    212            {           2606    52297    formato formato_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.formato DROP CONSTRAINT formato_pkey;
       public         ciecyt_admin    false    213            }           2606    52299 $   grupo_semillero grupo_semillero_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.grupo_semillero
    ADD CONSTRAINT grupo_semillero_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.grupo_semillero DROP CONSTRAINT grupo_semillero_pkey;
       public         ciecyt_admin    false    214                       2606    52301 *   impactos_esperados impactos_esperados_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT impactos_esperados_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT impactos_esperados_pkey;
       public         ciecyt_admin    false    215            �           2606    52303 .   informacion_pasantia informacion_pasantia_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.informacion_pasantia
    ADD CONSTRAINT informacion_pasantia_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.informacion_pasantia DROP CONSTRAINT informacion_pasantia_pkey;
       public         ciecyt_admin    false    216            �           2606    52305 ,   integrante_proyecto integrante_proyecto_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT integrante_proyecto_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT integrante_proyecto_pkey;
       public         ciecyt_admin    false    217            �           2606    60809 *   investigacion_tipo investigacion_tipo_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.investigacion_tipo
    ADD CONSTRAINT investigacion_tipo_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.investigacion_tipo DROP CONSTRAINT investigacion_tipo_pkey;
       public         postgres    false    243            �           2606    52307     jhi_authority jhi_authority_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);
 J   ALTER TABLE ONLY public.jhi_authority DROP CONSTRAINT jhi_authority_pkey;
       public         ciecyt_admin    false    218            �           2606    52309 :   jhi_persistent_audit_event jhi_persistent_audit_event_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);
 d   ALTER TABLE ONLY public.jhi_persistent_audit_event DROP CONSTRAINT jhi_persistent_audit_event_pkey;
       public         ciecyt_admin    false    219            �           2606    52311 @   jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);
 j   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT jhi_persistent_audit_evt_data_pkey;
       public         ciecyt_admin    false    220    220            �           2606    52313 *   jhi_user_authority jhi_user_authority_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);
 T   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT jhi_user_authority_pkey;
       public         ciecyt_admin    false    222    222            �           2606    52315    jhi_user jhi_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT jhi_user_pkey;
       public         ciecyt_admin    false    221            �           2606    52317 ,   linea_investigacion linea_investigacion_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT linea_investigacion_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT linea_investigacion_pkey;
       public         ciecyt_admin    false    223            �           2606    52319    menu menu_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public         ciecyt_admin    false    224            �           2606    52321    modalidad modalidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT modalidad_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT modalidad_pkey;
       public         ciecyt_admin    false    225            �           2606    52323    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public         ciecyt_admin    false    226            �           2606    52325 (   presupuesto_valor presupuesto_valor_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT presupuesto_valor_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT presupuesto_valor_pkey;
       public         ciecyt_admin    false    227            �           2606    52327    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public         ciecyt_admin    false    228            �           2606    52329 (   producto_proyecto producto_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT producto_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT producto_proyecto_pkey;
       public         ciecyt_admin    false    229            �           2606    60821    programa programa_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.programa DROP CONSTRAINT programa_pkey;
       public         postgres    false    245            �           2606    52331     proyecto_fase proyecto_fase_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT proyecto_fase_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT proyecto_fase_pkey;
       public         ciecyt_admin    false    231            �           2606    52333    proyecto proyecto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public         ciecyt_admin    false    230            �           2606    52335 ,   proyecto_respuestas proyecto_respuestas_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT proyecto_respuestas_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT proyecto_respuestas_pkey;
       public         ciecyt_admin    false    232            �           2606    52337 .   resultados_esperados resultados_esperados_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT resultados_esperados_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT resultados_esperados_pkey;
       public         ciecyt_admin    false    233            �           2606    52339 (   retroalimentacion retroalimentacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT retroalimentacion_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT retroalimentacion_pkey;
       public         ciecyt_admin    false    234            �           2606    52341    rol_menu rol_menu_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT rol_menu_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT rol_menu_pkey;
       public         ciecyt_admin    false    235            �           2606    52343 $   roles_modalidad roles_modalidad_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT roles_modalidad_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT roles_modalidad_pkey;
       public         ciecyt_admin    false    236            �           2606    52345    rubro rubro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rubro
    ADD CONSTRAINT rubro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rubro DROP CONSTRAINT rubro_pkey;
       public         ciecyt_admin    false    237            �           2606    52347    solicitud solicitud_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT solicitud_pkey;
       public         ciecyt_admin    false    239            �           2606    52349     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public         ciecyt_admin    false    240            �           2606    52351    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         ciecyt_admin    false    241            �           2606    52353    jhi_user ux_user_email 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_email;
       public         ciecyt_admin    false    221            �           2606    52355    jhi_user ux_user_login 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_login;
       public         ciecyt_admin    false    221            �           1259    69007 :   fki_fk_linea_investigacion_linea_investigacion_programa_id    INDEX     �   CREATE INDEX fki_fk_linea_investigacion_linea_investigacion_programa_id ON public.linea_investigacion USING btree (linea_investigacion_programa_id);
 N   DROP INDEX public.fki_fk_linea_investigacion_linea_investigacion_programa_id;
       public         ciecyt_admin    false    223            �           1259    52356    idx_persistent_audit_event    INDEX     r   CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);
 .   DROP INDEX public.idx_persistent_audit_event;
       public         ciecyt_admin    false    219    219            �           1259    52357    idx_persistent_audit_evt_data    INDEX     k   CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);
 1   DROP INDEX public.idx_persistent_audit_evt_data;
       public         ciecyt_admin    false    220            �           2606    52358 R   adjunto_proyecto_fase adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id FOREIGN KEY (adjunto_proyecto_fase_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 |   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id;
       public       ciecyt_admin    false    197    231    2982            �           2606    52363 Y   adjunto_retroalimentacion adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id FOREIGN KEY (adjunto_retroalimentacion_retroalimentacion_id) REFERENCES public.retroalimentacion(id);
 �   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id;
       public       ciecyt_admin    false    198    234    2988            �           2606    52368 W   cronograma_ciecyt_fases cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id FOREIGN KEY (cronograma_ciecyt_fases_cronograma_ciecyt_id) REFERENCES public.cronograma_ciecyt(id);
 �   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id;
       public       ciecyt_admin    false    203    202    2919            �           2606    52373 $   jhi_user_authority fk_authority_name    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);
 N   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_authority_name;
       public       ciecyt_admin    false    222    2949    218            �           2606    52378 ;   categorizacion fk_categorizacion_categorizacion_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT fk_categorizacion_categorizacion_proyecto_id FOREIGN KEY (categorizacion_proyecto_id) REFERENCES public.proyecto(id);
 e   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT fk_categorizacion_categorizacion_proyecto_id;
       public       ciecyt_admin    false    2980    230    199            �           2606    52383 F   ciclo_propedeutico fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id FOREIGN KEY (ciclo_propedeutico_acuerdo_id) REFERENCES public.acuerdo(id);
 p   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id;
       public       ciecyt_admin    false    2907    200    196            �           2606    52388 5   ciclo_propedeutico fk_ciclo_propedeutico_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_modalidad_id FOREIGN KEY (modalidad_id) REFERENCES public.modalidad(id);
 _   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_modalidad_id;
       public       ciecyt_admin    false    200    225    2970            �           2606    52393 E   cronograma_ciecyt fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id FOREIGN KEY (cronograma_ciecyt_modalidad_id) REFERENCES public.modalidad(id);
 o   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id;
       public       ciecyt_admin    false    202    225    2970            �           2606    52398 S   cronograma_ciecyt_fases fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id FOREIGN KEY (cronograma_ciecyt_fases_fases_id) REFERENCES public.fases(id);
 }   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id;
       public       ciecyt_admin    false    203    211    2935            �           2606    52403 /   cronograma fk_cronograma_cronograma_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT fk_cronograma_cronograma_proyecto_id FOREIGN KEY (cronograma_proyecto_id) REFERENCES public.proyecto(id);
 Y   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT fk_cronograma_cronograma_proyecto_id;
       public       ciecyt_admin    false    201    230    2980            �           2606    52408 (   elemento fk_elemento_elemento_formato_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_formato_id FOREIGN KEY (elemento_formato_id) REFERENCES public.formato(id);
 R   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_formato_id;
       public       ciecyt_admin    false    2939    213    206            �           2606    52413 *   elemento fk_elemento_elemento_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_modalidad_id FOREIGN KEY (elemento_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_modalidad_id;
       public       ciecyt_admin    false    225    2970    206            �           2606    52418 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_elemento_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id FOREIGN KEY (elemento_proyecto_elemento_id) REFERENCES public.elemento(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id;
       public       ciecyt_admin    false    2925    206    207            �           2606    52423 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id FOREIGN KEY (elemento_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id;
       public       ciecyt_admin    false    207    2980    230            �           2606    52428 L   entidad_financiadora fk_entidad_financiadora_entidad_financiadora_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id FOREIGN KEY (entidad_financiadora_entidad_id) REFERENCES public.entidad(id);
 v   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id;
       public       ciecyt_admin    false    209    2929    208            �           2606    52433 8   jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);
 b   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT fk_evt_pers_audit_evt_data;
       public       ciecyt_admin    false    220    2952    219            �           2606    52438 !   fases fk_fases_fases_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fk_fases_fases_modalidad_id FOREIGN KEY (fases_modalidad_id) REFERENCES public.modalidad(id);
 K   ALTER TABLE ONLY public.fases DROP CONSTRAINT fk_fases_fases_modalidad_id;
       public       ciecyt_admin    false    2970    225    211            �           2606    52443 4   ficha_tecnica fk_ficha_tecnica_ficha_tecnica_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id FOREIGN KEY (ficha_tecnica_user_id) REFERENCES public.jhi_user(id);
 ^   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id;
       public       ciecyt_admin    false    212    2957    221            �           2606    52448 F   impactos_esperados fk_impactos_esperados_impactos_esperado_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id FOREIGN KEY (impactos_esperado_proyecto_id) REFERENCES public.proyecto(id);
 p   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id;
       public       ciecyt_admin    false    230    2980    215            �           2606    52453 J   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id FOREIGN KEY (integrante_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id;
       public       ciecyt_admin    false    230    2980    217            �           2606    52458 Q   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id FOREIGN KEY (integrante_proyecto_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 {   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id;
       public       ciecyt_admin    false    236    2992    217            �           2606    52463 F   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id FOREIGN KEY (integrante_proyecto_user_id) REFERENCES public.jhi_user(id);
 p   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id;
       public       ciecyt_admin    false    2957    217    221            �           2606    69002 J   linea_investigacion fk_linea_investigacion_linea_investigacion_programa_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_investigacion_programa_id FOREIGN KEY (linea_investigacion_programa_id) REFERENCES public.programa(id) NOT VALID;
 t   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_investigacion_programa_id;
       public       ciecyt_admin    false    245    223    3004            �           2606    52473 9   linea_investigacion fk_linea_investigacion_linea_padre_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_padre_id FOREIGN KEY (linea_padre_id) REFERENCES public.linea_investigacion(id);
 c   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_padre_id;
       public       ciecyt_admin    false    223    2966    223            �           2606    52478    menu fk_menu_menu_padre_id    FK CONSTRAINT     ~   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fk_menu_menu_padre_id FOREIGN KEY (menu_padre_id) REFERENCES public.menu(id);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT fk_menu_menu_padre_id;
       public       ciecyt_admin    false    224    2968    224            �           2606    52483 +   modalidad fk_modalidad_modalidad_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT fk_modalidad_modalidad_acuerdo_id FOREIGN KEY (modalidad_acuerdo_id) REFERENCES public.acuerdo(id);
 U   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT fk_modalidad_modalidad_acuerdo_id;
       public       ciecyt_admin    false    2907    225    196            �           2606    52488 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public       ciecyt_admin    false    226    2970    225            �           2606    52493 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public       ciecyt_admin    false    226    236    2992            �           2606    52498 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public       ciecyt_admin    false    2998    226    240            �           2606    52503 C   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id FOREIGN KEY (presupuesto_valor_entidad_id) REFERENCES public.entidad(id);
 m   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id;
       public       ciecyt_admin    false    208    227    2929            �           2606    52508 D   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id FOREIGN KEY (presupuesto_valor_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id;
       public       ciecyt_admin    false    227    2980    230            �           2606    52513 A   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_rubro_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id FOREIGN KEY (presupuesto_valor_rubro_id) REFERENCES public.rubro(id);
 k   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id;
       public       ciecyt_admin    false    227    237    2994            �           2606    52518 D   producto_proyecto fk_producto_proyecto_producto_proyecto_producto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id FOREIGN KEY (producto_proyecto_producto_id) REFERENCES public.producto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id;
       public       ciecyt_admin    false    229    228    2976            �           2606    52523 D   producto_proyecto fk_producto_proyecto_producto_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id FOREIGN KEY (producto_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id;
       public       ciecyt_admin    false    229    230    2980            �           2606    52528     proyecto fk_proyecto_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);
 J   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_facultad_id;
       public       ciecyt_admin    false    230    210    2933            �           2606    52533 5   proyecto_fase fk_proyecto_fase_proyecto_fase_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id FOREIGN KEY (proyecto_fase_fases_id) REFERENCES public.fases(id);
 _   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id;
       public       ciecyt_admin    false    231    211    2935            �           2606    52538 8   proyecto_fase fk_proyecto_fase_proyecto_fase_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id FOREIGN KEY (proyecto_fase_proyecto_id) REFERENCES public.proyecto(id);
 b   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id;
       public       ciecyt_admin    false    231    230    2980            �           2606    52543 0   proyecto fk_proyecto_proyecto_grupo_semillero_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);
 Z   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id;
       public       ciecyt_admin    false    230    214    2941            �           2606    52548 4   proyecto fk_proyecto_proyecto_linea_investigacion_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);
 ^   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id;
       public       ciecyt_admin    false    230    2966    223            �           2606    52553 *   proyecto fk_proyecto_proyecto_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_modalidad_id;
       public       ciecyt_admin    false    230    225    2970            �           2606    52558 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id FOREIGN KEY (proyecto_respuestas_pregunta_id) REFERENCES public.pregunta(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id;
       public       ciecyt_admin    false    2972    232    226            �           2606    52563 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id FOREIGN KEY (proyecto_respuestas_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id;
       public       ciecyt_admin    false    230    232    2980            �           2606    52568 M   resultados_esperados fk_resultados_esperados_resultados_esperados_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id FOREIGN KEY (resultados_esperados_proyecto_id) REFERENCES public.proyecto(id);
 w   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id;
       public       ciecyt_admin    false    230    233    2980            �           2606    52573 I   retroalimentacion fk_retroalimentacion_retroalimentacion_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id FOREIGN KEY (retroalimentacion_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 s   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id;
       public       ciecyt_admin    false    231    234    2982            �           2606    52578 @   retroalimentacion fk_retroalimentacion_retroalimentacion_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id FOREIGN KEY (retroalimentacion_user_id) REFERENCES public.jhi_user(id);
 j   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id;
       public       ciecyt_admin    false    221    234    2957            �           2606    52583 %   rol_menu fk_rol_menu_rol_menu_menu_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT fk_rol_menu_rol_menu_menu_id FOREIGN KEY (rol_menu_menu_id) REFERENCES public.menu(id);
 O   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT fk_rol_menu_rol_menu_menu_id;
       public       ciecyt_admin    false    224    235    2968            �           2606    52588 ?   roles_modalidad fk_roles_modalidad_roles_modalidad_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id FOREIGN KEY (roles_modalidad_modalidad_id) REFERENCES public.modalidad(id);
 i   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id;
       public       ciecyt_admin    false    225    236    2970            �           2606    52593 7   solicitud fk_solicitud_solicitud_integrante_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id FOREIGN KEY (solicitud_integrante_proyecto_id) REFERENCES public.integrante_proyecto(id);
 a   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id;
       public       ciecyt_admin    false    217    239    2947            �           2606    52598    jhi_user_authority fk_user_id    FK CONSTRAINT        ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);
 G   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_user_id;
       public       ciecyt_admin    false    2957    222    221            g   8   x�3450�(*MMJTpL.M-J�Wpt	�4�4442"N##]c]#�=... JNL      h   �  x�}SKO�@>O~��W~%!�@--)��fw��Y�Z�p0��CD+�0'[���vGN�	�,�e]eU�����i0�%
=V��������!�Vk�]C��nͨ�&3y���c} ���z����7o�˲ma'*�(X��(���E��xMG�&����)�<�<u7�?�P���f�6+�Y?�c�qr���O�ZJs�v�)���Rz�y&mE�>�~2x��?�!�ѳ�Pm���9�Ɖ��G�u�"�rWD��&�M\$�}^�n�5b6�w墊xE��Y�M	[ӳ�$'1{%Xڿ���e��D��K���8eC�,M��ś���e]�]`�y�=nG��Q���5��/�%㥤1��^����>�L$vv�s�V���𜁣�T<<gv�~ͽrs�]�V�@��R���H����ۜ����C�^�i.�v�.��G�VZ*��9�s�� �U	��g�~H�xU�k��T�8�a½�_��Q��=9݉��|J����_��.�      i     x�}��n�@E��W�\XK�Rq��A��lh���f�Ȯ����E�z�� qH�{�(�מ|05��t1_$�4����IQ]d����m�cOgؚ�Md�<����Y
Ob.�q�a���`���?O��,���XƟg7���&�,��8�Ȯ:0�g{d���v�>7>��Vܝ�h�Jh�?x��}D}I��%8=�2��#yY�Qh�Tҳ,��f���#i����AL����ĩJ�iD�r��։���n�r���-�T�����kF*�˴\��<)�F�D=�z��s�O�
�N�ݠ� �dCp66.+���W�L��;1��hxt�n���o�.`>�����0������r�0[uz؋iZ���G��PqP���S_�j4i�S!��?���+8	��v�$)K���)4	f��5��_�h��M��ν�����:v;Gc��*_-�*?7���^5kGc���k:���]��u���k=l�o���_f��ȇ/�      j     x�=��N�0�ϛ�� ��s�AQ[����^g�Zr��vR�ӳ΁�=���\[2#��,������Xgf�uu��In���J����K����t�H��ە���%M�My1Es���v��Voͤ�O�%{Rm��^�Noہc"�/��qGnjwVB�qs��"9-��^^ˢT�c�8���xS�#��g�����LS�#,��NhpEB�j�>6Z�c	Q��";�V�mH�ݓd{�e�H��b��J�(N���UU��~�      k   '   x�3430�(*MMJTp�L���4455�\1z\\\ �C�      l   �   x�M�]� ���\����K���	����ﶡb�Cv���,�)maVq=�sV$ ��+my��u��$�H8����K��h$�~����8�Q����hXv�3�B���V��$��][�i���1-j*.��Ϳ���x��{mrDX����W%�t�!QE6F)�	�D�      m   V  x�uQKO�@>/�b� ���c�D$F4^�L�����4�[�S5����0߻o6�J>!�jŖ�4�ޠ�������92XUAЖp%�����}A�yYtf�A� �W#��C��y�|��(?�aͱ�9l�l�_��y������c�lj���0%O�-L�,'���-ņ��d�J�rv����X�%��$0�����5�����6a��Wg��F;��=��kd+u����ܱm(ϵ���AwHb�����=�!�M&P�_7$fƺ�6��i*��gݗf�$�MƂ*�����d�>��/��{fWj�`(Zt��D����q�%���I��� ���      n   �   x�u�=o�@�g��T@ү�6�:u(k�瀕���(���CO���y��6E[�uU7U���vf�:���)��F�����U�fd��"t�w����$ m�/���؟W�Ӷ����_zWEoN=ض
�%�9C/Lg� i����9�x��UFu!ѯ��Q]D跔x4�A��q��o��H#�C��37���}��:�}�zT��+�EL��,�_$��      o   a  x��]YoG~n�
=�Ѥ�Ë}{al��؇ ĈɓP�2$�x�V��9z��:�`��aO��>����������}�훎,������u�����w���O��Yo��=A��ݷ�z�[~j�����5��k*�9���������?����}���o�%���]r�r��]�0{�V�5����Lߐe������C�Y6W�ǋ���Owͦ����#���t%���V2#���'�2����]��}��n��E�Z5�E�ٷ�ϋu���O�}�^7�6�����F:�.����z�-Pq��o�'��|��S��ֿ�a��f��(�g�]�<���*Ȉc�`��9i��ₗ�����Ii�cF�����)�/G�4�u˔6���YS�t.�2+��\vM������a�iہH��N.[�*>t�}�}�w�9��ݶkڻ�}����]�n�W^Xg���jyn|��o�_.6p竫��Rg��0��f]�[�6�����fyh�Uh��*�a�7������d�f��>�o��z���>4��o�a��T�p�0_�����E��L�2-�>{?6�������`e�� ��;ǀAU��]���*Y���:W�:�]l~x�)g�eJ�Ӽ����Y���V� �V�L�n���uup!6��RN���`y�+]	�2$+�d�����*���pF�%���hN�0Q4���'��.�#�Q�A/
?��h�����(n��}h0ي

�<��=t+멷M0��[�C��|��;x$ȉ�RHK�J�(;\�B���a��C��M�,?�<)��vmi�T_,�]��{��}�~ڮ�e�?�漭�4`��>vO]�0bЌ����������^�d��q*�P7�2�1��O��կ0����9���E7����O�Y,�`��yw��6�F��	'4x�4e�8�:Kv��5�����ٽ�-";������d��4��i�ri�K��b�n���?�ݾ�;�vѮ���ۦ�0��牶�WQ}w?���S��FӔ�f6)WY��$a�h�i�J�7|�vAu��U���' %�-��tL:'-Uג����o>\S}��G��Y�=�9��A��U{��Iu�7�C_M����T���rZZp:��C=�ٮ���ֹØǬ��=�B��(8j��Bs�[�, ����;�@��\����l;���e'y�F�p�Y�ج��u���_����ia;S`/$(+��ơg<J��Ki
�� -�7��f��ͦ�d��M��E�/V��0�G�%�]	�9�~��6h V�e�ay��[�*X����ƛ��0]0|�XR6�����X�bISr��>����F��	��>��b��!�	'�`}�sU�)�'���Cfh>Yŉ���p���t�hI�) c5������&����*�т�k��=��^"�<.4��Yb��除:�#2EJ;
��2�]L�AF� �Q� hW��Op��빀�R	�L��=��Lт�1�H��1í ��H�LXIL�(����@�3��"��Жd+M��X�8�@9,jL�FO��IJ3�kИ�)v�#�
�p�oO���m��sξ�lTuq��S�%�cNjD����`ٮ���%�,F���n�^�(`a10�c�ɐ��$U���^\4:��L�SH���C��/��#)�Id*2J2���QL8�8ю���d[C-�_�粚�n��X�%�LL	.�L����.����2�p*+�NS�\]��Y��a��'sq��<�8�H�W�*[�Yq�k��,S� �L��&R��'џ͜��S͕*$�|�9�R������9RB$�}���a�WJ8S�*"��+�lEՅ�b0/���TJ�p25r��.����#w{wx8����O��vA�@C�j"�Q�鸔B4՚)a���:�=.��]f��~�,Um�q�Ȕ��8Qu^/E!�|N�RT��(����C�t�k7�f��b҅紑,I�y������J4Q¡�P���8���%t[��h�'��`��dn��*�I��Y�z� a>�
lD]�	RR�_.��:	*!*ŋ|b&�>>��i}@��1DE��9���*��c�,��׉b�&�����1FT4}�����m�':�{ܘ�G�I,$S>[���:��^ Fa��S��l���0��'�$:��!N�P��M��~��;u8 ��~����,���&k�(���AΔO?��|��U@�<�M�h��V�����B^�.�h,iǕ":)i�w����;�S2ΐ�Y.� ��r"7�p4X�@&���D4�(�_���Bhx����� ��>Xh�Ʌ43HC#U��m��3	�ƙ�e�.ͅI.a�:��@�o��Yb
���ʲo�r��M�HM��rW��2>��N[�c�0�8��7������VZP��)�A{�68`e�J�0�mAy��<J�6��(�1���s�/q��Ւؔ���)�y��A7n�u�[ ް2��(Y�*�5q�C��(!܂C'q�8y,�1�&�K�C��PN\
62W)`^ee���i�h�$���Wq`BP <I)�e�D=��r)�!� +a<�\�w-���,7�2��9+�	��&���S�9��hŔ�@
a� J`h(A��(�މb4�$��-n�����T��� 
�d�Y��2d˗�����������f�g0Z��s���lj�O�e4zdf\'z6!k����;���s�/;)�"�/¤c��	��&0��%��c Y��|"_�7��w��'�����ޯ͓���*e7 }>*��Tg��6]g�h����e�����b=4��@�S��TY�w���O#tu�L���������V�����-��ay��n�JS�����Qa��M%tM	q���͐hs�*F�bF��yW Oa|�������3)§$���cVJ�������!�?O�П�-2j�Q�є�k*Z6Ok�9��s�o��5+�3r3s'8���3���!*�rS[�\���i��;���?�v��w��\��Rz�2��B�3-GU�S��\����>��۶ڿ����6��-3��,p/��|]fҗ�4�Q�+-)����y�� ]�@�S�4}N�[��hƋ�\�r.\
���^�!�cTxJn�����_BF3tQb��UVZAa���i��էD7����|	�b��a�~N�����4X6���F�����~Pң�S���s
c��aP�Ro�rW9�Z&0��D�|j6L4��w|�MHE�/����e�c��!�ܛkQڽ���N��8c��>�>�r�Z?^�P�3��,�Uc2�(3�9B��Q|LKna�K���TO&`e�{�8�
��9p�aSdU�Opi�XJn�Da�(%(v./��^�� PŹ�4�<��g���c<�qɖ�i>j�c�$�XZ.o� ��3=�v��p����z��*�
R\�~��;3��A����,Ge��V�N�L���3��!Ǽa\�bҺ���7�'�`j��^L�aa*��,�\��/�|��Mb*�ߟY'te�UR���{����f�S��&��3��*��5M8p�]H�UX.y;�Ӓ*Rr0����$X"S2��j��P%D�����YbR�>�ޓ6��#ЁQ�����\ulQ���(pv�ݤ�4�n��_����w���彻�	F��!01�sZ���_^\M�/ᕜKA�ޣ4�K9����D�wA����d��H����w��b#5�E���0뼿$�6��'F��&:J�^�&� D���dј�bb��L􉙑�9!�����	����k-��ӝp��( &�9����"��'Qk�|,S`��^z�dD���Sx��ŉ�eg��yL�;ca㈇��gv��u���z�����N�      p   E   x�3�,�4202�54�5�P02�20�25�320��	�q�50�pV�0�4�34��35�3������ w��      q   �  x��ZMoG�=�~EI��#R���֣���<ޛ/ɪ�V	Օ=�U=l�u�a��\��ؼ�Y��$5��w0��Y�/^��̋�O�6tU�;�5�:�676����3�k���uc��-�?<_\<|ry���������F�G�7�u��4���i�q�mp��7��4|�l�wփ��Lp������T3㿝�oVM��ڟ=x|qy����Z��9��b�ஜ�.�����=���ύ7[,��.���m��pxew��zl��	��?��
/b�V�7�7qX5�ջ��	|9��6��3��l+�U���tXGp�~eۈM��-޲}�Ç�V>p�=��=n���[Lc��qՀ�omW���a�[yXH��9LlCc��lh��v��5��_��&r���i:���~@Bmk��6x�1�l�\:�	X�J8t<�ç����U�6���zX�	*ozwx��/��z�80���*��D�����B�F=^�j	$K����U�l�_�z�z���yb5ω7l�����әUc�+ŀ�s4�ٶ�Dw��{�?l�k �dƈ��Dy�g�O�P�m�8zL&�?�Mea  xgk��L�C��m|<�rA��-�R�t�}�gY5]�c��y=nw��a�����ۈQ�k0}��]���``�gp���װ�ӀE@�E���#�_K`FLW7ꮥ@�n[l\g�
��<�~�&�iO�������8���m�4�'��7��X�jBx��q	�.��5�`o%"�aGV�tB|�:>�f��np4�⫴�h�yn�rp�a�h�m}.J6������^����,�+��wY@[��y;���C�V���_����+�J�f���s[sА���A���W���;���7_��c�`��yt͐���Z�Bet�z���J����tک:����t4Oi�|��o#��_Ƈ\��Z�~��o4�σ=�9�ـ#Ha�D7��mko��h0hnb���,≐����q�#��B5�7"�wU��������3�}������3��Vl�>�~O1����؝�������K�r,�ۑ���w#��
���V~'a�'��[���(����+V��z��Kgo�]??�4#唒}sDM�ɖXLԧD� H��?kO+��,z�9�$!� m��,�z2��/rۊ!	�^x�!Y�B���_w�f��Jb4i��&��Y`��
��u]���0�0?�HU�����K��ӏ����y��Ɛ1����c���>�!�^{�Ρ�����G��6	f>��nH��A'���a�X���w���x��9�<T��vx���(,����d���k]�M��F��ԟr�pP�[��B[�c�_�T����]�>����S`q�Z�����#ԯ�)<1�	eM����)��x({�f��f�č�h��2�PQ�KjX'4kGԪ2�L�ݢ�3�a��E��e�Kd��U�9�k�{F%����=җ�s>���U���.��kJ���X�e��-��L����h0) �ֶ�*J�#��&� ���m�|"������*���Ͳ,��E���4�Q�>:R��7�(�樵�u��rˣ���:���6Z��-Q���C�Ћ�x?W��)�l49�Ok��`P���I�n��5-t�AQ�f�$T���E�y	]� "{���$<r�o@M}����RǓB���ʂ��_�.������*��	��M�-g><���Df�I3/u���.֬�*��4���|fo��	pk8u��+�81X�Nni�1IH�N�#��4M�k��y�~�؛y�w�)�N�so�����Wys��+��f<�@&��b����N��Ӛg���66���' ���ˮ..'���e e$�LFH���Ԡ�#���Lv�;���H�!��%oj�V��I����yU����..��-i8�.���Y)Ӄ�Q��c��[���+��k�į�=�_�X�Q���¶��
�]k��_��[�IԿ��s�J����r!o	��2ʆ������!���[-�Նĸ����Qs���PK�]�+,atJ�:�K{�I囄g�,%��~�w��Qb�$(�epJ��؋t3���Ϯ��U_���1� �LR9c��? �!��[����Ql��;�:�����_��X����ݞN}2���hf�r�u���]&����a��GQ��}_ޣ�"Ӽ���en7�^�B/]+�����u��0��\$G!T]�ŋ�P3��S�	�h.A9+�ZLTU��f>`qh7�b����-�OɎ� aL$��(�6�) d�;I3p��͋!e��/H���1w�F�+�!��edkfaˬ�=�(��f}�x�3���w�g�j�j���r�H箛����ۑJ�%}��Z�V�����[����v&fl\$J5���7ցI���2>i]�b���46�P�n��u�֦�/ͷ�KN}�j+�u�'�i"���F����J�u�E�]�v2`���g��� ��/��J�jz{\"up�٢]�E�Ue(I�$��f@\A@ތB�*t�q��L�55@���V0:�dϴM��'��pN:S� �ȥe� f��R����C|u~I������"��3r-���� �3�GR�e��Wp�Yl~3�b\m+o���MrGKǨ#g�|�4�e@M��.j����ЕG��y���!���e֭Y�����E)KP����$�z�CKC�1��Q�*��
�6�Q�#m�]��׸�B>��R9���$�p����~�-It-��u;[��i��e3C��p�}�촙���u�^���Rv����(�Y��3�bf��f�I�l��i�׍v�6>qB�]/�_[sG�{�V;�5�Bu�$Y�I"p�]"�bN2�,�rLp�{���ϧ��g��T4����Q���uOZ%kI��v�ɾ/�F�n~��g'��9�7W�Ì�*���v@��(Y��<�ظ7>�ϛ������ōcP�!ڱ����@*EQ@�G�b�D�_��_Ӗi����7�Kz��H�BK�uS�4]��7Y>iZ&@���h��Zedg�5��:3����-w�kD��n�Ъ}�l��(�9�\Fd�J��O�PX����6e`�OEW�P�5Ħ�)�!m���j�Zڮ���'�͎��,_��;-��G0
��<��B灖��=�OY���������1�x:L��䕴�gS*�MJ��؂
Q��'!x�̤�L*gd'�UP�CYe��j"��uS�oK�g�UO� �4NEjK�ZD(����^�O��'�|��$k,1c�Ts��t�G�"QNN�����(��5v?��8��z=���&�uZ9���OG��p%n��H�e����9���k<r����(�*�2nS�?I�;���8��	�a��ZUM�4(s7��W�ɎE�/�ԝ_i��_"�k�����K�o�睃�EUV��� �^��ʺ�ތ��QN�",��1���E���O��ڙ��SE�����HB�O�rǸ�1�n}x���}�f���>�Ӥ���P� ��u}�����Y��,۽�堞^KP�w�6�����S�}c�:j�(�m҄�l��Q�9�[98a���+�T���X[=4er�y���Uщyr��W�;�QWS�U���~��{�����8�T�����U'sG�A�b���+�A�,w0h������٫g��׶��v�ljϰ{�h�P>�.�,͎���;k:mQ���?�ܬ�.1��=*گ��5Q��L��T��F�qq߭������~�g��,��ey$V|�0�����������Gt�8x���/���_`���V9j݊�˅��J�X�S�=��1�oWB�߮���.�/qzʧ�݆��6�?�mh���;�C+f5��?�{O�\מ �K^w)�m̂����=�tvv������      r      x��}˒G����i܈4���Q���Ig�c����3��&� A%2���*��%Z\�n6m��c������%f�DV�u�U@>"<��D�d>�N���&/v��&.$�mw�>I��v���u��URnl�+\^�|��<M���[��.O�P�Ӥ���	���\�/��/q�)���-��|i����xF��w�<��r�� pa��O�I�&��^F��Ȩ²0�Ef�ĕ�,ٚ���~vv�x���&/Ja��kw�r����
�&K��U�,F����d���2kv�m3�,��eW�0.�Xlx��(���rc�M��7x����.p�*����8$7��ȯ^a����e��$l�-C��y���΄`�4���7x���cS~*�.�U�̅�~U�\�9����|��|��2�R^�
��A�Ӿ���lz>;�\^�''/��d��*5�ԑ*u�W��A��:R�.�*u>�ԠRG��y_�.�T�H����|P�A��T���J]*5�ԑ*5���l2=	��*��O��Φ'���w+�4E��$Ǩ�KM���������ߺ����Y����K����g�`ms[�擼��^�����o~������Ё�s7E��ǧv-O��C��l�%�2j�%9��s�	T&'��v�tv>�4`齱�����
5D%�B�P}���|�d4jBK_|��gxI&\�e���>aȽ*y�J��j��:N���
5԰�:N�f}�B�A��S�y_��4ޠP�)�U_���C�	/�����e����Y���I�w����fo��<����Ǐ���/��Wݏ�M�Dz����~z�����������g�ߟ���8�~��������������_~?�}���1��ۮNLHW&mg\լ�y����v�w��v>>��G����Ju>��k���d<$�cvD�N3���'����nLIt&�4�Xf��]�Q����=�T@�~� '�H��}d�T_�Ÿ�t�T��-3܈^����
��4��IRW���d�7d��ɖ���1�8
��W�X(�}�/M鮩�~Q��=6w�?Md�C?��#M���/[(�W��F�I��MTcSdۈb���b�.Ԧ��Y��dbT��]��@�(:��y `z#�����-�P�-��4�#Bvk�%����V�%L�_��|0��T��� l��*+M����I�4�X8	��N��[�� 5ؠ߁Z�҈�5���~�5�ܕU��s`���ɏ!��|z�X�n)�ơ��s�&5����9|���8�:V���:F)��e^�e��_7�3߳q����#�h]A�p�(S{�~��u񏳲a���_@)��K��T��`�5�)'��>Ě-�̯C��б�id#�f�a����j�������+��j^"vdO��2�%��G��"��yָEѺ�:uf�z�eC���V?�����e�yqe5�z ��>�r��g��V����ҧ��������i�va^ɥk�D�4�ZjAOiĸP	lLP�<�<ig��?;4��MG��2��Ƌ�#c[������!��r2�z��ۡB���l�s��6u&o#�]f�x�R��I},�]V�<#׽F�]��`�!Ā]����pK��}v��ZX�-�����;O$�\�A@P�ȱ3q��,�3��D�ML����~,}��MN�*]�B���	��z�aѓ�d�[�˸�6����ɷ1���i/��s��(�5��T��_Z���Zp�J2��Q[�~�� ���:�Q#���T�SҼh����*X/���M=�QI�5��a���u:���3��*i�G�qiw�ZI�b�m��4�@��/d~�݄1�G�rW��^�O�,���h��y����E����|��n�>��������zO~��<���le�x���)������p������Y4/���׎�]�dk�%����k�U���	����xs-%�FW�q�o\�z[V�۷y�p "�&�����t��]
�_��o��&&��'ߪE�����h�w-�3┼)�k�g����B+�j�]P�㊆�0#�+����c75�ΐ/a-��[/�#���p0������:{�V��pk�K�2ʏzY�;��4�Z�F
R�a����O*F��.���]���Ug��јҮ��3�ZXL��A3�w���pX��:C6��a��,����-�u��&�.��k��8ǯ�0�ś:= ���"�Hf�=�(V:�ࢫ��6��?���l<k�2��L:O�ޑv�t�g6�c�L��ꈌ���m��6�>�\����Z�|������m��D�a�y�{׸�!�B�?[H?_I���oH�$�?2R>3�`"�a��46���EX7��\��\OEH����A��ɅV�֍��bp��������I����ˡ������EG��U�=��(6Y��罙]ę=x��me]���>tZ:��$s%3�X�?�:_@��H�]e��B�������*Ns����������;:>p�������U�ͩM���o���Ĵ���|P!�d�#򳮺>l�:�3��(�� x� ,�z���!C��Ղi#�����erN7��UKކ��@����mr��WQ_�H��4�S���n�����x��ʥH�
�^��Q���. B��P��
5ET̲u���X��_Իp�����u	.����=~�f`LYR���Z�թ������b���rWt>�\�F��K�/Z�&��M /�	#��[����{���*.�(U�Xzz2��NL�<��#O����5��.�:
1���s~K5Yg��N?��gá�ö���ǚ��A�p8;��!���R�q7�����J�ԧf���o��p�m�P>��E4����b}h��c��7z$��C }�񁧷����MG���|�@@8�p:���NC��é��ß$���@�/�aC9��U���L�`�>H��PC}5���@q�'����U�8z+t�%Q`'$0���+MUZH�f.T@�H���n ��hE��N���2�*q%����~��qfB�|I����K��]Uf�]��B ��P�7�0	�vK�.��X_���z*� �����k�~;&h��RP_�_:���[���>�3Wm�c�z<�oB��_����ꋠ2��w;�D��G`
�Hw���5k3S#~�dK���l5,�BEF0Rr0���o���;�<�,�3���jg�V{'#�/�fG��ң�����ڮ��5�r��t2�������D�ɥۗӳ�	�̇S�8�i�Ӈ��JP��4I�C{À�c�?����`�Z���� �c��gZ-��@2ӍO^���{����oV?�8�5��?�f	���Q6��X_F���x��ig��袤^s`��K\�e��f�9 ���:�l5��Q.�ߥ��a�SuPdX�⡆:h�Z|��A��%�ݝvɷ����y<{2b��� �б����C�� �c�z��z��ۏ� �aU�jI��hIĶl]�r:��4[w������ԗI�e��6�bI�@��ɴ���c�-��vv^S{�l6��0�G!��M_���ʶ�B[;�)����Y���Z:2	~1�V�a �����>��͛�S�+NN������;�LH��w԰��}zޚ����8@�"�>yH���aC�b�ap�y��ɣ�+��o#�]��1�g�3�,[���ei���I�΅���d�I�M��1���J��:���.i��B8b9c�����r��n���-��������ӗ�˜�Ode5{�eф���Q�������q I���Xn|բ�l���O������$�\X����౶܂�KvU*���h�1,� :0����`��l����9���V��Xi3N��XV[)�[|Ux�[�n�ؒGCY��U��m�-&�UbB�?Һ�EԘ�җ����,l��o�Ʋ��������4kG��A�N����.�A�O�h�2��DX�,V6�~#[}���k�Ϯ)�    �_T�J*���kGj�s�u����m�{��l���*(���;[������'��9
��䌺I�P"��=�
��v�vW=sK8g�ɖ&��@^$�^?���m�/�� ee.���g�Q�h,�;���7����u��m�w�Q���Ơ�N�[�~�<	%-��9�%W�ے�}�?�pxo>��[��k��(m�}�V�s�o��9�UA��TT��,�Ʀpa�W�վ��yc���T@�q�S!���-��%7Y��i���qc�`�r�O.��L��4;h���,�w��xY�hxU2�3�J�P�N��bs��X���k�y�nNS��n4yԑ�#�������AA����_��9�8�$�2�h��o�]�����L��KVQHE�gSm6;M�E�]jl���w����4��,�b5�N�j#���nrzkm��Tm�o�������b�ΣYzAT���F��Ku���p����¦w�3�}�F�w`�l-�L��k ]V�_���JʰM�&�/��t~O� �q�PfZ��ҏZl���x9y�+�T��
6Gm���|��B�Au!ժh��s���ڎ�6J޷����Y�"-�y�sN�/��6�z�-�
��'	g�A���b �@�d�'�eH����R�;�Z�����K��6$��Բ�؍�by�fZ�Чf)�F�t<��������%k q� ��M�_�gU��-���,u��R��o��]�7j(k(w�&z�m �%�����v�צ�sj��jo'�&��F�F�C}��[�6�_Ϯ�a#Sp�N����I�� Ma�wK��ڽ��XMx�{�Ou��k��k�6)He!go$�E)i��KZ�E��ho!�k����7FPЪ� 7��1~�
��&���Y�8xR*��|��t�VH��C,�i��V2P"5=ճ6��IJ��ɡUE1�B0��&��Q��̤�t���2K�j������S��Gֈ��Y���}�c�s����+߆M��*>|�8Tk�8ԡ'#� 	~}�����J+����+n��p�ب�NGB��#�tJ�Pۑ���hn�xM��]ps���pG�v�чV��谚��_�e�L'#-�)������W�>�f�E���4I]�%M�-�S��A����g����WJ���Z��i@d܅律��t�~��}
#ۚ"Y,�t�Q�Z�¼i�����E0�GO�U�A7<�;f���c�-&�2~�����ѽ���J��}�ь�������`�^��B�W�P`��}�崼���׎�x��S�,u��i|ۋ�G���!�Jl%�՚ �+=����$ÝPԮ�����ia,J��j8���j�������� \�G�_J۠��˕�ap�\��HO0�q���d�u����Ӡ�\2l�?Z ���������v`ycT9�.��:]ڈ��A�n��'ٵ+��$G%�VuVc�9��JL-�{�wrp�,�4���=�Tx&�Θ�M�JS�u��6̴�X�s�X��e���;ͶN���FL�
�۞e�����U��ɩ[���0*��p� F����	�d�.�iuX#q6�۷dft���AN���W����^��i|�;%�suIF�{��V���E*e�bϷ�OH0ych�d��Ĝ�M.Fm
��2]��I3IA�j�k�Gv�"���25�p1⎏*���*���B�o���������r�����9������U�t6�8���"T�k�_�<�`H�Q-��}*���}-n@G&�,�Ǹ���0��@�]_1���e��5H�rxa]���h�y�X PR���E���Wօ	!D�մ����2
ZsXR�b̚���.>�ɴ]8���6���c�<�FQ_�alD�$	ky�P�V���-N(�&�M����Ʉ5�����u�/��	}��}����J��in�ԑD9�V�L�,DD�Ul�"@z%4�����˨�u�_�tCK�V�.�ǆХ�8羣���M��q��D�%������-��$D#MJB�Yh�B>���&݂�k ���)����Ϫ��N�ȗ���e%��5�H�ci��7���3�l�n��QҒg�f��U^ի��=u��M��^���SX�(�X�R��L�|,��b�3���x��C�E!�uj�ǺҊg|/F��6<'�Y�`%�j i׈��w��rR)����l���J=��Ş67UW�����|ӘXZ�3P5̩�.66��
(�!���h��Q�~���/��n��8�T��kl�Ig:��B�޺C�D��~���E�mLr�����Kn
��L|wJD�Yۈ��Rڸvq&<�.7�ĄY����P��
�a5��Wk:��E$�,�h��m"���e=ڛ�׈�6�.�$9d>�b�|�����l?��oN�m�L��ֹ�^�3phu�	�0�,�rO��hr��FiD�P@{w�]�j��Q�dw��(�����x�;�3�1�Lx=)IV����)j]cך��ג)�"o����`��Ml1f������ZU��d(;)
ɧ�$�(V@���NKP�3�fYF�n8�>��:��'6�I���S0�2uJ���}XF<}m:�"#�ȨnL�'�NS%6�]�vma�rn�O�n�8�rAȦ��kay[wo�K,�Y��n����Ȇ�,k� ����r���L�=�zRH�@��7V,y\�Q��M�ԋ���dV�~޻�;��\�A]�&qGY��8���k�9ۥY�&��`��b��9�d�$ƪ���s��F3b���ڇF��0�����tpӷ����T�u��Ź@+"C� W}>l$ՎiT#U�Iv�l8R��
�ȥ�����fY����J�U�a;�[6x��Ngr���܃ە����N�t&ݍg�I����A����*-�*��(��~�Dt.!J��ê3T,�]���L'�3�����ƙޗ�k�"�y�j9�j�x���jq����g#zJq�p�����x��
��������YF������IH�먙e���!<���}��9?	�k�4�W�Y�"��?��U�����]���ɑ�O}��/�Ы��t��>��No�9�rS�j�nA�T��>��i���c$�|�>ǋ��|�`Ո��X���*�,#� �;NN'���TƢ?���Giہ�U����B>J��{.��:��>��<�vٓd�זK��M��RZLu�N��n\VCa��C���
��l��V:�F�ҪP����a�)G�rE��D�L����f�������F]�}��*��j)I�3��p��d��]��!;�T�#�^���+����4���v�w����N#��DI�`����q�ղn���Hs�`)��<_�y�tJ��or���L�^�:�(nL�Yg�n~�ä��hY���tq�T�f�Z�g�%;| 6X�e����e	���Q���+��!��uIDC���c� I`S������T�`O��*�m頗[��1a����P�P���eE�P6h&:��CN[���?}�Gv8jF��>�h�W�z3��W�������􈱁h��]��m��(G{#�;?�^�ve�u�c&[m-S. ��ݧ�-��Z@�ęl9$�2�)̥��A� uE��C�<�@c�6�1c|&O�b�Wɮ����`���J圶�cf��N�����T[�F�\-<e�D�u�5�@�ہ,����wy���JY���h\e�L���ǲ�8����5�k�2#�d�� 7��ܫ���ġk�����H".1�P��`n)�+	υ�ĭ*��?� �׬*fn�a|-��C[%� 2��G������Myyn�0> 	��9.���H)0�>����l\��Mt��-S>%��K���߭�	�#;xAO��Rz����6�Wmtʁ�@<k�X�r��"U(�ʶ/9|�U��`��T;��Ԥ��m2I^0���*�j�$�I^Pof�X��ȥ��bЖC5���]�EXyq�7fOm#sֲ���Ӡ�矑5km��+j!(6��G�G��N���������a�}��e�����M� �  �ݒMW���<��w�W�ݕ�ɿ�X6��&��oe{�ѭ�$;4)��K�#bEOH�'����H��ŉ����O}{p�i�5	퇩.*I�e2\	�i��[#�v�gl�d��i����X]i���Y2_���"8$�l%yr�\i��nY��t%���5���Vَ�\�ɜ�$�l�o�P*.	�R� �}�=}����~����6:mC�:N�J�Ȩ����Ca�lE��wB�K.&�
�n��^XnSv����*]��)mih�q1+M\rk��w'��Y$�`"�s�,N�Zf^��'�3[�;6��.Yz-���e���Y9g������v̲o8�L�IƖZ��R~�w7GcCN�I�8iS�K�����x����p'�&D�C����{��t�l��o_�A��ȝ��1�.��}x���l�ǉ��uݣ��>��>���]��x����&p���d��0�T�]�܏v�b�6.���6���N�$t�J���+�*T�J<ؾ;� ~�	�`��*1�\s"(�a���x���Or�3��ðx�Mƍغk�0.h�I�ƺ�/r��F�k�Ԅ�u�0�U�TÞ'�h`�Q�+�"%�C\� 7�<���C@E�)��܌b���9g�^ nf��g�+	A��>��e��#B).#��)�Lg�{�Z��;MBqK=;4|���)�e�'���+��Lȝ���8�NX��k����!���A�9�ގ���������ಱl[���ɾf��gJ2FK�t�5�و��kjW�A�
�<;3�7��~���yW>M�M:�BFw���NL��`���K�����Q^���p���q��%ݥM�+j�Qaɽ1�(�Ǽ��{��I7�7�%O��Gt3�K�.����vJQ�V�M$� ���Y�W85n��e��z���`�"1����r4m�ur�;�*V��S �ȉ��$�fA�`��?·�5��8Q����m���H��^x9����ƱI&���������}�����ȈmO~Q? sk�)�0�"�G��ʓ�!�-B������v۸+J�b��$���?�t�q" �l7Qb��*`���*���c J�~��/����Jh�p���(M�w����%����q��ڗ�)S�L�b۲�0�����0�R
ǳ����2	͊2B;��;!��OVppR'M[y���F�� &t=��'5��
��NoS�!<�c�k���~K�cĊi��V-��"K���u�O�m�n+as���]k���ZF�~��e�M�gnb�ٵ�^5��mL��x�ޣ~c��	��#�scz$X���tIz���Q׾k�.�V��u�?ӷ�`@[Y¡g.�q�+�:��*�Mm�ƚ���9h���=�|T�3I�;[��咹��K�?I*H��%EUX]�%�{�=Vm��jА1��/Xt���F�?RW`�y�o�}�O��&�@�[KG.�$5Q�=y���6�K!�⼉�:���r^�SBL"�،E8}E�HV�n��1:;ZD�P��s����䥨����,��l�����<킅.�+�����;�@Rj�n-6��u��e���8��ľ���nX۔S;ss��!��\#���k��i�Tz�9�ϓMC;��>��(*���G��B\���;#�J7��\�!3�������1����-����?�ɉ�'�˳�����Gu`�qG��rv�@��/����%� ,�8�BH<����p�����aP��Q5�ʜ�sr�	�w�1�m�� X��V�X+w����,x�Ҁ�c�0�r⇻�R�4�4��H<]E<}4��!��{��O�s���f6������\����|��o�gGЃ�y���|:����y��ΧC!k�ñp�5�����s��w�)}<��y{��`B�&�"�㡗���!A<��X4]F4-^���5����U�X~��5��#MƼ&]CGן��k��F�؊�Y��2��>�����O>���n��      s     x�5�KN�@�מS�	�Ͳ�]E�e��L��jbG�ކp�^��������*�����/�G���Zr���+x��*6�	7��֘K���)&N�$�k�h��v�S�����xB	����h["2���rR�#�~�qO6	A��n�5��-S�fD�Dγ��ز0�;Xc���!�����۔J.�
d!_u�,�?��C��ò-��|гU���=@u���3�ҁ�E��m������>���0@I�J��-.O5�d�yB����}@H�����ׅs����6      t   x   x�U�A!��yLE�ؿ���5���8�#E���>����K��Jx��&:��7\�խ�B-� �?1��$�{��BC���ٙ��)|�ܲV3W)�����z �����W)�y�-�      u   X   x�3175�4���KO��L-:�6Q�R�935/93�X��������b.#SCN#Nǔ�̼�⒢���Û��:��$&�s��qqq Q|      v   �   x�-��j�0E��W�Z:}w;��nJ�W��(��q� ;��׃g��s�� 6���;-�3�9N����<B�^FL�2��=������4�[������鈣��&�@ǁU,���%EӚ�ʿ�//�<�)�O8���J������Y�{�TNv>^8a&u|ߡ�/�n�;��u�cQ�P�m�^�f�eZw���Ji7O����qa-eX�-�
��Y�e)      w   �  x�=��v�@���S��
�4-�BCOsNW��3�-2���}z�4����|�^�����
��J���w�����R⌃Ă]b��M[�D��V�sX�	[rۆ���H�%�+p�t#�٨���4�n���ӈ��"�k�wK8ÏD�x�~����4��Xht�����K|Ӗ�����P~r)⺗L�g��?�#�l\������3�iR�l��z�юZ	RF|�X�):ƅ�ZL�fl�w��K7#��]�l�tp���I_C�������9K9�)J.����ȟ,>p�4~���W����%Lm`��*�v���D˻O��"�NL�H̅`j橳�<{<�Y�����ӑU��.��~�b
_Aw��5�{���ly�5�L����޴p6��ς�6��y,{ۋ����?n�3ڸ0}��^�A[c�&�_b��h���"��ȑ�mdQ������46vfv�V���l6����_      x   8   x�3140�(*MMJTHIUH�/�M,��4�3�3�0�4202�50�54����� =/P      y   5   x�3355�(*MMJTHIU(N����I-�W�L�2���Wp,��LN�b���� Q�)      z   >   x�3�045���/J�U�,().�U0�4B@��[ Uq5��5�4F@�B4s��qqq ��%'      {   w  x�m��n1�g�Sh�䃾�|�dvat蔅�ю ���;e(�~��qR�F!R�@��篷~R�E�$v&L���Z<ő|^ZK�2��O~}�3;^|��3-0(�ꤖ�P��+�!St�g������b(����ɱX���R�/�8&��:���>Tl� �ղ���?dC�k)�LxdPJۡ[od8N~�)G9
0����^��-�������}��� ��x,�S~���7@>!}���qa�����R*m��+s������w���Y<���g�r�ͧ{h�5q��"�[z�nw�bf��o�"쥱��R�� ]�Z��zn/i���8��i��겶3�0t�ISo-��u?l6�s�4�_��m      |   �  x�}���0��)fec0N[����Ş�ǉ��(�`������߿�'b�^��0K��_���v9�Mo:�J�8��k�ߵr�5%�̛Q;v��*Z'tՐ��������'�	t�ٮ6�-�ۑ��6Ӊn9�j����\��բ�G����Ơb�%�P(A�\��#dl �`�l����.����i8]�� W%���`���ŪG����5��콥<�D��Q^~�S�qK�!y�~!
�^#���q���̔���#���Y�7����6�P*������ZBkq�Ԕ��ޔ�-�[A6�Ť��*ZI��l{��k+�T��H^_�i�OQ�
��4���Ntð��6CE�#�b�5��+w�@��*֢&
�fH�����B]j�m�O�6$�}/�}����gG�LАz���d�վT_t$�^��^T�Yʱ��!������dj�h�e��A{�\gdtړNP�`m�b�ց�ʇ�}���[�f���c�d:�%�0���6�}�݉�8^�u��)e�D��h��Z��]�(-�r.ƪ0�y�VE��pQ�����#���66D	��1��+�Z�7�����tj��Ԟ~�.�^$�͠ÛA�A���/T(�K��g6�����5,4���.j�b�O�V��o�tn|�P�dyċ�z}��lg����!&����	�TS��=��tت�:����|�#�'      �   W   x�32�40�I�/�LN�,.)��K�,(�/�/�,ɇ
pUr&� �$r�$*d敥�d�'&g��)�dR����0����� ��#�      }   G   x���q�wt����
1C�]� ,�`�`(�����/��q�tu���]�CB]<�r^�A�.�\1z\\\ �:�      ~   �  x���ъ\7��w�b^ Ʋd�>w!�4PRh��BYh.�R����3��9:}�ot,K�~y��L_��=?=�\�<^>��q�rN������?�}��ݛ�������޼y���cZ&�I)mO����/�~{;I^��F�(�<<�����|�&�Qo�J{TN$�MF�E�h��խ��{b
Qw�~�8�l�$��=�>j?��+�&�:б�('����B׍Z�}0��_���i�֎,'���D%�����}!�)J	W����dY���sD�e	�9�Ak���bA���4�`��6��Y�U	P��љ,e#��A�I5�I>oՊ)q	���j_[P�9u�H���ڍ�Uo�ur[��T�%[�sߪ$��FM�<��J�g��5�}T�$��*��z��*�U�$j��|U��2��8B�;+�Y-0nu�{��a�f�j��V}��l�_�k�@�V��(US�.aT]�b��n�v�`�6�Wu�U�:����[AԶ.�.>�2�&����]��pv;i��Q�/���ntE��2�Q�PE� jP�+8O�n�r��
6��VG��$���:��_�qA�����Q�TǺ�t70.(D�[�N0�}�_��H�;M��!�}�_Qs�� �65��Iu]�Z����:�[���zu�.�Κ�FW//��T�S#�j#���w4���l���!����	(^Q+��VUwZ���"lJ��A�ԕ"P�hVRI����<�0��u-��7,����q	P��g��Yn�>�:'d��@$Z��l.�B����wX��;G��a��5�M3�,�A�.�Ão/�mA��y�M-��r7�.����B��Z�e!�s2/؆�[�,ض?m����H�{�[����6^1C�M#;��C�Z������{��!ku����ܜ%"0y��r1������;��li�&�l�hD`���yd�� S�F��0�G��ꣃe�~��}�}0�U�{�9;Z��e)r��0�0��bn�c��b҂�xR��w�1@]�R�S�i�@\�67 /V��y��x,l4\�mD�Wĭ��u��G��:�����ױ�ۻ �'m��[H��M�8ў�w1/�}1k	?;�w"�rHV��C֭
��&���:� uAV�ѐ쎤bK+&�c�V�ݠ|��6��YUGʴ��z}����{�ޖ�{��Y������z��9�<׸:�G��B�
��5N���ܧ��_ԋ�ekVF:�W����[A��C_V��n��܃M�n�0hb���W��6f� ��Pi��}�O,p	���T�h?a�_��<�)�jo.sim�J���2a�h�&xnE�sHT`�U�΅6DUV��?G\kA)���E����������ȏ�m��4_�ԧ�uS5	Ի����3�����D:�         �   x��ϱ� ����' �e��-\=�h� ���-,N0��]�/7L:��{@.h}0V?�\��DP[0i'rKO��(����*�[�%O�;�_>��Pr+�(5����g�VЩ'�s���X�}1^�Ό���ka=?+�*&F*:b%W��c��f�      �   @  x��Uɒ�8=��%#|j��^ C�6؀@�������5��BBI�O�2���~L}C�7H�{ �]���:7w'Ϭ����?j�m���5��k��<�L���>=�G�u� �E��S?������EQN��k�˼�}�� �8�_�Z�槰����&�5���X�-C`,n^��zM/b�R���k����/�g(����2�"��Z��D�]_�R%�c�_Y�-�e$�3�f���D��"�������K�|:F�p��!C�@\n�ho�8�Җ�>�t7��}�{�ͽ:lMgrj���?9X�MB��\&z���X���wg�rMM��-N3ek����z����}}3���PRtrnJ��-+C�x�9�����i�)���������h,�to�n�)�n���H��F3o�݄\U� ~� t ��ช[����g|ײ`1���_u�m�]�\`�ґd'A��C�(&�x3�;�e睮Ԭ�����9vN��8��{�����g��yJ����.�:�������K���6i���� Z����?ojp<)R�>)��3,�؜C=p���:���
3e���M6J"�W�\:�l��m=��� K��G�"�>Aﺉ�"QdW���~�N7|��kҨ���3��i��MD��4�Γ\qU�0���u��mAķb�+ٮ�6Gv ����R%�e�n�-�¯�XA(������\�NU��nd�=E�	*2em%�spW۳��k�'�!�C�����T��C�L��i@��9��><��(�1A��vb�/�qb� ���xJ��=p���8�lG��C�/�mjY#L�!��U��M:et\��K���.��#�DL��;P <?�3��_v�y!**���˙�7�7�N��;]�s�\Oa��!�n)�\e[���i��z�TjZ!4�>I���1ۉ��:�;��'�/h^��������n>X�p�+��n�_�����#S�1¤Rѓ�%\��(�#��m�/�a�U��2�ed��aj�4�-�u��0�ޤ�]�Ѥ�}������c��M�,�mxsc[�4=����/o�o�v��;,���v�,��bk���T���Lx;���g.����u �%ScL�k��l�b�}�%C<�����;�g�f��h+�7v��Q:z�쫊�]'v�6`9��uw�誁�B%L?̯O�=��+���b66ӣ��\,��m_��x�/���*�N���t:T�BhZe�EЬI���7Cu����ǷO��m�YNٳ%ur.9���ЄB�����v�}8T�dǈ���h���      �   �   x�e�K
�0D��a�lG�^�X�6�B��~bb9�i�0�F�R����GX@��fN`�a�1�[#��R}pKa �]��G���1���H����[M���fׯ��́�g?m!�kE�����{�j�A����:��C_g � b�      �   <   x�3560�.MR���KMTHIU((*MMJ�44�4� ��r��>�J8c���1z\\\ 
��      �   	  x�}S�n�0<�_�c{p����X$A�6X��m/��u�ʒ ��YE?!?Vʯ���7���C:Ϫkأ����щ׿
�nt�?|'�P�0��C�U��xQt{��e_�0H�S>5̹mk�?ל��ဪ�<���Qpf!5sj�'���xO�2�.`�;��^���"�	�'�����R����y�WD��YV�EШ૮�5���vN�EN���n�Jk�����iIC��t�WFe��p#��v�-�Iq�.{��eFѭ�-[S�ӘK�0����h�������PM��V��g������0`�c�	]��s��d<���8x������r���Nx�-NG2�g�����!V�&'Fvn���2�n���y/l�{d֝�3�J�C�~���u�
=��^-S�鷵Y�P�����q�	5=��-�0z��3g�+�����O!�f�)��Q����[�W����[(� J ��b�!�����j q�
)�n��,����o���7cvMɣ�(���{<      �   Y   x�32035�H,N�+9�6�������������%� '?71%!h�铙��������W�Z\�����yxsD��)Ь����b?F��� g�      �   �  x��U�n9<�_�c^E��9��6v$Y#�ї�������`������>���Ƕ���ǖ�2Cvuuu�x6y3�j���zu��D�ٽk�QYR�@����&U�wQ�����Y,_T�Wǚ>(��-L�g\M���̪�M��+�XH[
��lx{įe�z|@\m)�1VY�����r�O��7���Q���f��Z�(�[ܩ�ՁdT�ȿ�*s�V�%i�nܩ��7�~&'������x:�L�o�v�
�����p�m֜@h�2��"6��X�\R����vk��$�*
[6k_�5�/���}8��$�G��l�F��d���!��j��aǪqrm��Ty�ȵ �%)��}((��=*)�f��%ц�W�d��b��������a8!?y7�����e{O�7���;9�KrQ�8���%E�M�YM.����f	<Ǌu{�0�GQ�W^X�vht�*�n�FK�����lJArOO���we%�J2��/x[xam�jS�99�\PH�����F�F�L��}�xKN7�&���~��k�,Z\C��w�����I[��W��f��Q|[�����cj6�^��	�Y��4�XE�L��ijz�$ɥm�C�P��[�.&��v��y��S�H�$p��$@!>���?�u��Ac�����9Q-��d{��g�����	6.�˜{u���}s�1�e�rc^<��+�~7U�P_g1R���4�wF�
���{<���*un�������o����qj�.p�+��uW}�B�q�e:�����&�^��E�^�����І�5�6��M��H7�N��3��.� �
8KH�Ft�P���w����&�٣��\�N)�[�~O����\"��� ���*�ð�K�K%G+M�;u>�v�ևO�a$� ��ބ���p�¸��{PL�57��һ��n������v/��Gf�2��܈���~�To���1�B���>�ϭWhv�Ўd�3�ϙu�M��NRz��g
:]���zu��&�R���JHO�f�1J�d_�(9S�H�����z�܀�������;���\~6�?b=]c�䙌�E�w�#F���ˬ��1�6�ߞrֿ�맧m��ޒ��~�a���!Vxwp}xpp�7�t̮      �   �   x�}�=�0�g�=���i'f$���%�Ia@ ��i#T������=+�Z��?�{u�j{����*��z9�츠��+���dP�"[-��[RiقӖ�z`f��"���:���`��PQ�ZC)I9���vAEBah*Y���LK9��p8���%5J9�rdk@� i+��,�O�9��S*5@��C�a����.Q=~�~e�y��t�      �   �   x���n�0Eg�+�)������4ݺ��E@�
�__u�ӹ��a<��a�pn9T��dYv�v}��9m��o�XR����*>�(��Fq˼j ����Wk9�T���]�ٸD|���.�>�gM��բ$��Jq�N�gB�/�V.�R_a:Mo4�63�ւ�{�E�gw���"��lH�      �   �   x�5�M� ����@m�_�Fwc�n(��H���BO��@B¼�f^ޔYۛ��	�}"(^K�'�\�hB	���+*��� 4N��3������Ԛ�X�Y�ܐl�mR[���&	��d�	�7N�3d�=�s����H~�fr ��ѻ��>�P�/��,W�mz��9k>Q[�	�[�i�(���T�      �   �   x�u�M�0���)zӂ�&�ƍ.�40�&ejZ��P�8������y߼׬TR�3����1�q����DV]��m�s�%i�nP�w�%)�8A��8�8v� �k�x�a���4_EgZJ5�s�o:��K�5DY���}��b2�s~J����`���.���K`k��]������iS��Pm8�_�TcE      �   ;  x��Y�n�6}���OE�*��%@Q8�Ӻ�7	�(h�ڥ#�
%��y���}�'�O�%JZ]�7j?Ր��.53�93sH��u��z�'D,�Gy�<�f�,��P���*���Ֆ'~�g��!o?�?:Ô��뚚3vm͵�o�׉	f���&)�p�|�(��0����r�������|����[fd�n�'9��b���b��$)ޮ��6���H9��%H���[r>�D��8�.6&0cs�ru�'���Q��#l�0@IË�����#��1�|w
0�*zjUp@6	IDX�ͫ�yƊw:���vX	�(�2Ɔ�'_i'Ϟ>������?�����t3���%�8��2,chL�֨x4͡喺� ����F��:�C�akP �I��+��3�|d~�o��T
�>m��@(�R�n�̪0]��������LW$�U�t���{�+׳1HBϴ�'���Ɛ5��i�#����S��;��?��	rL��+@K����U%���ʁo����y]w�FS��z�E%$�>��UP���X�T��s�R2��G �`���4aj9N���f��F�����%�\5��>��z�+%Д;����b�X(EI���Er���WaAUˀlC��!)^��6�֊givCr�u�So����>���z����+��a�1��B��{o	�;w�/��Rލ`�9bg�ݚ5Nj� �Rϳ�bFw��چˬ���V��I�+;L������"��{�\u��vsg��^�l��ġ�������~�����|�_tt������|��W�K3�������E���%���!J�<����{���:{sq�������t]��s�
�Rr/��
�4�2��Ta��<����׿�Vs��\QH������,��%4O�Q?oat)G2<@�n[�C��c=Z-iDg�YK� 7`~E8"d?�Џ0�s���w�MD�� ��}��t�-�}*n0�����L�8$)�1��t �"V("��*JE�$���C��Q�"D/!B" ���8��<��4��r�<D<��zF�B肠��t���b�  w������} �P�E�(�Vz��xa+]:!q~�?��.p���)�i�"#w� �)8� 瀭4ڻG��D�l�Uڭ��ӫ�Y�f&��6x��:T��ۥ�5���������rx������e,��r.���xj�k����V9���D%��G�`Y�o��<
)#�<��mVim�Ŷ�-�a�ۋ��6`�U8����4�u�g`�O�ljCx?��gR�̌�r�S�"Nl���vT��G�(U���}��g9G<�j*H@a�d����91�Sťr�995�]�\��*���K<;���5kT�3�r?�7�GAn3����?"睺/�׮ޤ�Ǧ�#�#3�r���	6KPU$a?��C���EC�\��Ia��&�Ǌ���,)����p��� p[�r�>R/�`JVc�o��P��%�c7�i���m��Qci]��?�� �	��JR,�)vK:̱
]k�hV�pK���l]�M�6��j�T����d=���6^-��U<7i��+�+I�F�O�G���Ym�l�T���e�退�RX���d���B�OZҵ�������~&Fx�5[wk6豹ǗЉ��[k��!���˂�S�~ݻvW�C���*���rn�M@�e�I@��c�@yܘ�cm��VW��+�8�-��	Ȟn����,1���	X��أ�[��{�C��g�n������>7ܫ������Lh����n�ל6'�!��`ʛ�Ɵ������ ��z�      �   g  x�]�ݎ1���S��������"�E�Y�\p�&ƚ�9��ӯ��T�M��8��Fc��q�R��pW��a:����Ρ��SMʰ_�M�QRVq�W;\ƨ^�C�<�j��H�'q�n38j<(uO�*�Q�y�*q�!�,qͮ��s���X��NB�ÄC2�z�g�b첄�&�`���r/�2�s���%��Ϡ���	��|2��J`\���*����?��%��^�?���K�f�v�-g�3v�G:y��+���$-�����˚D�
^���ڱʌ��m.m�b�����*��t�x�z)��1�Ċ�����R���$��mٓ��d϶7�a���M��2������k      �   E  x��U�n�6}v��`lc�}�l�f��.���Ř+hR%)7���q�) ����$[��1Q$gΜsf4}����?>}��]��/(q1���}nS9p�����j�}�2�TU0
+e�����G��q�
�p�6�	���WZ"fTWZ��%U�� �QOF=�[U��t2z�����2�-�3�A{��b
�Nu��r6 k���4�{>8��B�?��#״�S��M�*`r�#�h%Y�'Z��M����d])^�G�'����hz8�DeII%���Ԣy�
�e����D|UsL�^qTl�Xp�%��q̪�u��+�x����ꯚ�W�J�ޫ��Ֆ��-k�Z႐��%�?��'#*?{�d�xZ�%C3����B��[�����hJҦyv�*�q�E�tzv2��x}���ƍ9<&�k�0G�xM�6�F&l�M�8���k��p�
��0���k�������S����b������X�N DL��+��|((�U�� ��$Eqp��<�B�?0Y���I^���xꆷ�G(s�6x+,!�,��YH��X "��%e@�:����~#�X-`��[_6?h�[��	&�]CC��tG��>l�����BG��ޣ-�{����{����(WKL<yuo������y��N`{�D�<�� �1�)c�x�4�%v����u�-XQ�����p�K�����|��O*���a�!�X���G#�(x�y�<�Fc��~��FH��ZALK�
��JZ*��q�FV_��#�2�;k�M]����r�f�M\0��D�5���6?��c�YM.���co����~μ�cmM;2n3�">�A|�O��	smk&n�X�]O�e�$�^F�e��a�Π�ɵm+�I^Ò	��܈o��������\�hXD�������gЌ!8���4K��c���A�^�!���|8��>62�3Ň��-0�j�n_y�<cv`��*c�i���O�ևdc���xn�#f�?�Xe�K0L��Z��-S�WKK��!���ڈ�+�L���kA!����oH�e+<k����D	����(�'kdB!aA�|y�������?jj      �   5   x�3�040���/J�U�,().�U �54�j1�2j5B�$ąh5����� H$�      �   �  x�uR�n�0>3O��`+Nl�n�rH7 ݭ�b-���I���.�C�@	����9�C����Zn#���>(�o 2�m3�ͪ۲yU��s:w70�������[DU�݂�%���9<�1*|b�;MHVbT����uҭ�c0��Pl��:���:�6��\g^d�XXh���l���p%�ꢪ�D�ʍ��^�ĿBW�:��~�d�~��m|2�
I/����ѻw�бeO�����D3~�n��
��PU��S�m�IG%=Md���i-������ff�2���(I9x�8��φ������&3�Y[dy�/�k�:[�:譛ˎ���x?�}��hJ{^�Q��Ce��g�r6A.�10�_S]��.ʏ	�~�l6^kκ      �   c  x�}��n�0Eg�c
��,g��c+Э_��G�P�ׄ�CZ�����M�ӏ?�����qۮS��3/����Y�[)UY<Ύ���e�ҪA���C�:e����s�P�̞�f��Z�b	1m-Ćbڊ���8g�3��eO�Clh��$,!"�� 6Tm�
�!��	)��KPh<����n.�!�� 4�pU��-���и����%lH<
AC��τ��[���X
�A^����y�f�*�Y
���>�0�u0cf�`A,��(Ð�(�p&���v�?N�������$���*̢pVa.E�
3*-�0��dfWzVa�eݯ�r�*,m+Y��m�Ӹm���j�w��z����m��      �   �   x�m��
�0Eד�)�4��̢R*��PR�(|�����ù37RI�dh�k ����,��wRf:N���AwU״ �&��M�fy�(j3ǕcD��X$������ښL^��M?
3����wG6���k�t2�H��.6~Q�ॲ�g�<Yo�m�!<����wօ̃z�cOr�      �   �   x�=�=�0���>EN���P1"U*bb1i@FmR��q�9B/�U�'��5l�-7>��*�)�)XAnC�*Þ�b#N���K���*���8�-%�*��m͎c
>�
nlX�]�2���s��cņdw}��������,7���I*\B杨��V��r+��%��5���o����� ?�tL�      �   l  x�]��N�0�י��p4��s1HÒ��xZkҤr����ED������,�:�#{���b䚒'�~�	a�S�#����r��_�W�k���4Î�sG��Oŵ9P��YQ���D%+N���SiX^�Z��J'�kwt;S����$��L��B���+V��0$������U$�@��P
������0j/9�p� j�K˪��F��	��bv��X��Ua�]��p<Y`�}���|a�����ϽnM�O^(�X$P���6�O#�e��n��!'X�'�ݍ�[����P��m܅�	�{r��vC�O��r-�E{˷�¾8j+AO㗱:��K�v����f�7�@      �   2   x�3160�(*MMJT(�,�WHIU((JM/�+I�,@OI,������ �C      �   '  x�e��N�@Eמ���x?�m�P�j�wb��x43I�w+/||��5��[׸�~j:���V<�R���S���h�p�uH��K�|	�s�e(�u�p(��Y�5G�R�"	�J�����8Q��`E���3�u0nj���!2CK���y�5�{�S��,?vU�T2�q�(���=@�̆
�"��X�j��a }4	����جq-$Y�{S�t�Hә��Z��Kl�p��4�'XtZ��'��$��=Ü�����^�ō�l���
bҢ^;��8kϰ;v�jO���9�冐�     