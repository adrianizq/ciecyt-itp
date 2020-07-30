PGDMP                         x         	   ciecyt_db    9.5.19    11.6 �     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            
           1262    16385 	   ciecyt_db    DATABASE     {   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_CO.UTF-8' LC_CTYPE = 'es_CO.UTF-8';
    DROP DATABASE ciecyt_db;
             ciecyt_admin    false            
           0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6            �            1259    16482    acuerdo    TABLE     �   CREATE TABLE public.acuerdo (
    id bigint NOT NULL,
    acuerdo character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.acuerdo;
       public         ciecyt_admin    false            �            1259    16643    adjunto_proyecto_fase    TABLE     �  CREATE TABLE public.adjunto_proyecto_fase (
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
       public         ciecyt_admin    false            �            1259    16659    adjunto_retroalimentacion    TABLE     �  CREATE TABLE public.adjunto_retroalimentacion (
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
       public         ciecyt_admin    false            �            1259    16675    categorizacion    TABLE     �   CREATE TABLE public.categorizacion (
    id bigint NOT NULL,
    categoria character varying(255),
    descripcion character varying(255),
    categorizacion_proyecto_id bigint
);
 "   DROP TABLE public.categorizacion;
       public         ciecyt_admin    false            �            1259    16490    ciclo_propedeutico    TABLE     �   CREATE TABLE public.ciclo_propedeutico (
    id bigint NOT NULL,
    ciclo character varying(255),
    modalidad_id bigint,
    ciclo_propedeutico_acuerdo_id bigint
);
 &   DROP TABLE public.ciclo_propedeutico;
       public         ciecyt_admin    false            �            1259    16521 
   cronograma    TABLE     �   CREATE TABLE public.cronograma (
    id bigint NOT NULL,
    actividad character varying(255),
    duracion integer,
    fecha_inicio date,
    fecha_fin date,
    cronograma_proyecto_id bigint
);
    DROP TABLE public.cronograma;
       public         ciecyt_admin    false            �            1259    16612    cronograma_ciecyt    TABLE     �   CREATE TABLE public.cronograma_ciecyt (
    id bigint NOT NULL,
    titulo_cronograma character varying(255),
    fecha_inicio date,
    fecha_fin date,
    observaciones character varying(255),
    cronograma_ciecyt_modalidad_id bigint
);
 %   DROP TABLE public.cronograma_ciecyt;
       public         ciecyt_admin    false            �            1259    16620    cronograma_ciecyt_fases    TABLE       CREATE TABLE public.cronograma_ciecyt_fases (
    id bigint NOT NULL,
    inicio_fase date,
    fin_fase date,
    texto_explicativo character varying(255),
    cronograma_ciecyt_fases_cronograma_ciecyt_id bigint,
    cronograma_ciecyt_fases_fases_id bigint
);
 +   DROP TABLE public.cronograma_ciecyt_fases;
       public         ciecyt_admin    false            �            1259    16391    databasechangelog    TABLE     Y  CREATE TABLE public.databasechangelog (
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
       public         ciecyt_admin    false            �            1259    16386    databasechangeloglock    TABLE     �   CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);
 )   DROP TABLE public.databasechangeloglock;
       public         ciecyt_admin    false            �            1259    16552    elemento    TABLE     �   CREATE TABLE public.elemento (
    id bigint NOT NULL,
    elemento character varying(255),
    elemento_formato_id bigint,
    elemento_modalidad_id bigint,
    descripcion text
);
    DROP TABLE public.elemento;
       public         ciecyt_admin    false            �            1259    16557    elemento_proyecto    TABLE     �   CREATE TABLE public.elemento_proyecto (
    id bigint NOT NULL,
    dato text,
    elemento_proyecto_elemento_id bigint,
    elemento_proyecto_proyecto_id bigint
);
 %   DROP TABLE public.elemento_proyecto;
       public         ciecyt_admin    false            �            1259    16539    entidad    TABLE     |   CREATE TABLE public.entidad (
    id bigint NOT NULL,
    entidad character varying(255),
    nit character varying(255)
);
    DROP TABLE public.entidad;
       public         ciecyt_admin    false            �            1259    16547    entidad_financiadora    TABLE     �   CREATE TABLE public.entidad_financiadora (
    id bigint NOT NULL,
    valor double precision,
    aprobada boolean,
    entidad_financiadora_entidad_id bigint
);
 (   DROP TABLE public.entidad_financiadora;
       public         ciecyt_admin    false            �            1259    16469    facultad    TABLE     �   CREATE TABLE public.facultad (
    id bigint NOT NULL,
    codigo_facultad character varying(255),
    facultad character varying(255)
);
    DROP TABLE public.facultad;
       public         ciecyt_admin    false            �            1259    16599    fases    TABLE     �   CREATE TABLE public.fases (
    id bigint NOT NULL,
    fase character varying(255),
    notificable boolean,
    fases_modalidad_id bigint
);
    DROP TABLE public.fases;
       public         ciecyt_admin    false            �            1259    16667    ficha_tecnica    TABLE     �   CREATE TABLE public.ficha_tecnica (
    id bigint NOT NULL,
    titulo_profesional character varying(255),
    titulo_postgrado character varying(255),
    experiencia character varying(255),
    ficha_tecnica_user_id bigint
);
 !   DROP TABLE public.ficha_tecnica;
       public         ciecyt_admin    false            �            1259    16562    formato    TABLE     �   CREATE TABLE public.formato (
    id bigint NOT NULL,
    formato character varying(255),
    version character varying(255),
    codigo character varying(255),
    fecha date
);
    DROP TABLE public.formato;
       public         ciecyt_admin    false            �            1259    16464    grupo_semillero    TABLE     u   CREATE TABLE public.grupo_semillero (
    id bigint NOT NULL,
    nombre character varying(255),
    tipo boolean
);
 #   DROP TABLE public.grupo_semillero;
       public         ciecyt_admin    false            �            1259    16513    impactos_esperados    TABLE     �   CREATE TABLE public.impactos_esperados (
    id bigint NOT NULL,
    impacto character varying(255),
    plazo integer,
    indicador character varying(255),
    supuestos character varying(255),
    impactos_esperado_proyecto_id bigint
);
 &   DROP TABLE public.impactos_esperados;
       public         ciecyt_admin    false            �            1259    16625    integrante_proyecto    TABLE       CREATE TABLE public.integrante_proyecto (
    id bigint NOT NULL,
    integrante character varying(255),
    descripcion character varying(255),
    integrante_proyecto_user_id bigint,
    integrante_proyecto_proyecto_id bigint,
    integrante_proyecto_roles_modalidad_id bigint
);
 '   DROP TABLE public.integrante_proyecto;
       public         ciecyt_admin    false            �            1259    16411    jhi_authority    TABLE     O   CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);
 !   DROP TABLE public.jhi_authority;
       public         ciecyt_admin    false            �            1259    16431    jhi_persistent_audit_event    TABLE     �   CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);
 .   DROP TABLE public.jhi_persistent_audit_event;
       public         ciecyt_admin    false            �            1259    16436    jhi_persistent_audit_evt_data    TABLE     �   CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);
 1   DROP TABLE public.jhi_persistent_audit_evt_data;
       public         ciecyt_admin    false            �            1259    16399    jhi_user    TABLE     �  CREATE TABLE public.jhi_user (
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
       public         ciecyt_admin    false            �            1259    16416    jhi_user_authority    TABLE     {   CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);
 &   DROP TABLE public.jhi_user_authority;
       public         ciecyt_admin    false            �            1259    16456    linea_investigacion    TABLE     �   CREATE TABLE public.linea_investigacion (
    id bigint NOT NULL,
    linea character varying(255),
    codigo_linea character varying(255),
    linea_padre_id bigint,
    linea_investigacion_facultad_id bigint
);
 '   DROP TABLE public.linea_investigacion;
       public         ciecyt_admin    false            �            1259    16691    menu    TABLE     �   CREATE TABLE public.menu (
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
       public         ciecyt_admin    false            �            1259    16477 	   modalidad    TABLE     �   CREATE TABLE public.modalidad (
    id bigint NOT NULL,
    modalidad character varying(255),
    modalidad_acuerdo_id bigint
);
    DROP TABLE public.modalidad;
       public         ciecyt_admin    false            �            1259    16578    pregunta    TABLE       CREATE TABLE public.pregunta (
    id bigint NOT NULL,
    encabezado character varying(255),
    descripcion character varying(255),
    pregunta character varying(255),
    pregunta_tipo_pregunta_id bigint,
    pregunta_modalidad_id bigint,
    pregunta_roles_modalidad_id bigint
);
    DROP TABLE public.pregunta;
       public         ciecyt_admin    false            �            1259    16531    presupuesto_valor    TABLE     �  CREATE TABLE public.presupuesto_valor (
    id bigint NOT NULL,
    descripcion character varying(255),
    justificacion character varying(255),
    cantidad integer,
    valor_unitario double precision,
    especie double precision,
    dinero double precision,
    presupuesto_valor_rubro_id bigint,
    presupuesto_valor_proyecto_id bigint,
    presupuesto_valor_entidad_id bigint
);
 %   DROP TABLE public.presupuesto_valor;
       public         ciecyt_admin    false            �            1259    16503    producto    TABLE     ^   CREATE TABLE public.producto (
    id bigint NOT NULL,
    producto character varying(255)
);
    DROP TABLE public.producto;
       public         ciecyt_admin    false            �            1259    16508    producto_proyecto    TABLE     �   CREATE TABLE public.producto_proyecto (
    id bigint NOT NULL,
    aplica boolean,
    descripcion character varying(255),
    producto_proyecto_producto_id bigint,
    producto_proyecto_proyecto_id bigint
);
 %   DROP TABLE public.producto_proyecto;
       public         ciecyt_admin    false            �            1259    16448    proyecto    TABLE     -  CREATE TABLE public.proyecto (
    id bigint NOT NULL,
    titulo character varying(255),
    url character varying(255),
    lugar_ejecucion character varying(255),
    duracion character varying(255),
    fecha_ini date,
    fecha_fin date,
    contrapartida_pesos double precision,
    contrapartida_especie double precision,
    palabras_clave character varying(255),
    convocatoria character varying(255),
    proyecto_linea_investigacion_id bigint,
    proyecto_grupo_semillero_id bigint,
    proyecto_modalidad_id bigint,
    facultad_id bigint
);
    DROP TABLE public.proyecto;
       public         ciecyt_admin    false            �            1259    16604    proyecto_fase    TABLE       CREATE TABLE public.proyecto_fase (
    id bigint NOT NULL,
    titulo character varying(255),
    cumplida boolean,
    fecha_cumplimiento date,
    observaciones character varying(255),
    proyecto_fase_fases_id bigint,
    proyecto_fase_proyecto_id bigint
);
 !   DROP TABLE public.proyecto_fase;
       public         ciecyt_admin    false            �            1259    16586    proyecto_respuestas    TABLE        CREATE TABLE public.proyecto_respuestas (
    id bigint NOT NULL,
    respuesta character varying(255),
    observaciones character varying(255),
    viable boolean,
    proyecto_respuestas_pregunta_id bigint,
    proyecto_respuestas_proyecto_id bigint
);
 '   DROP TABLE public.proyecto_respuestas;
       public         ciecyt_admin    false            �            1259    16495    resultados_esperados    TABLE     �   CREATE TABLE public.resultados_esperados (
    id bigint NOT NULL,
    resultado character varying(255),
    indicador character varying(255),
    beneficiario character varying(255),
    resultados_esperados_proyecto_id bigint
);
 (   DROP TABLE public.resultados_esperados;
       public         ciecyt_admin    false            �            1259    16651    retroalimentacion    TABLE     O  CREATE TABLE public.retroalimentacion (
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
       public         ciecyt_admin    false            �            1259    16699    rol_menu    TABLE     �   CREATE TABLE public.rol_menu (
    id bigint NOT NULL,
    permitir_acceso boolean,
    permitir_crear boolean,
    permitir_editar boolean,
    permitir_eliminar boolean,
    auth_name character varying(255),
    rol_menu_menu_id bigint
);
    DROP TABLE public.rol_menu;
       public         ciecyt_admin    false            �            1259    16594    roles_modalidad    TABLE     �   CREATE TABLE public.roles_modalidad (
    id bigint NOT NULL,
    rol character varying(255),
    cantidad integer,
    calificador boolean,
    roles_modalidad_modalidad_id bigint
);
 #   DROP TABLE public.roles_modalidad;
       public         ciecyt_admin    false            �            1259    16526    rubro    TABLE     X   CREATE TABLE public.rubro (
    id bigint NOT NULL,
    rubro character varying(255)
);
    DROP TABLE public.rubro;
       public         ciecyt_admin    false            �            1259    16397    sequence_generator    SEQUENCE        CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sequence_generator;
       public       ciecyt_admin    false            �            1259    16634 	   solicitud    TABLE     �   CREATE TABLE public.solicitud (
    id bigint NOT NULL,
    estado boolean,
    asunto character varying(255),
    texto_solicitud character varying(255),
    fecha_solicitud date,
    solicitud_integrante_proyecto_id bigint
);
    DROP TABLE public.solicitud;
       public         ciecyt_admin    false            �            1259    16570    tipo_pregunta    TABLE     �   CREATE TABLE public.tipo_pregunta (
    id bigint NOT NULL,
    tipo_pregunta character varying(255),
    tipo_dato character varying(255)
);
 !   DROP TABLE public.tipo_pregunta;
       public         ciecyt_admin    false            �            1259    16683    usuario    TABLE     �   CREATE TABLE public.usuario (
    id bigint NOT NULL,
    usuario character varying(255),
    descripcion character varying(255)
);
    DROP TABLE public.usuario;
       public         ciecyt_admin    false            �	          0    16482    acuerdo 
   TABLE DATA               F   COPY public.acuerdo (id, acuerdo, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    194   b      �	          0    16643    adjunto_proyecto_fase 
   TABLE DATA               �   COPY public.adjunto_proyecto_fase (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_proyecto_fase, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_proyecto_fase_proyecto_fase_id) FROM stdin;
    public       ciecyt_admin    false    218   �      �	          0    16659    adjunto_retroalimentacion 
   TABLE DATA               �   COPY public.adjunto_retroalimentacion (id, nombre_adjunto, fecha_creacion, fecha_modificacion, estado_adjunto, adjunto_retroalimentacion, nombre_archivo_original, fecha_inicio, fecha_fin, adjunto_retroalimentacion_retroalimentacion_id) FROM stdin;
    public       ciecyt_admin    false    220   �      �	          0    16675    categorizacion 
   TABLE DATA               `   COPY public.categorizacion (id, categoria, descripcion, categorizacion_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    222   �      �	          0    16490    ciclo_propedeutico 
   TABLE DATA               d   COPY public.ciclo_propedeutico (id, ciclo, modalidad_id, ciclo_propedeutico_acuerdo_id) FROM stdin;
    public       ciecyt_admin    false    195   �      �	          0    16521 
   cronograma 
   TABLE DATA               n   COPY public.cronograma (id, actividad, duracion, fecha_inicio, fecha_fin, cronograma_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    200   )      �	          0    16612    cronograma_ciecyt 
   TABLE DATA               �   COPY public.cronograma_ciecyt (id, titulo_cronograma, fecha_inicio, fecha_fin, observaciones, cronograma_ciecyt_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    214   )      �	          0    16620    cronograma_ciecyt_fases 
   TABLE DATA               �   COPY public.cronograma_ciecyt_fases (id, inicio_fase, fin_fase, texto_explicativo, cronograma_ciecyt_fases_cronograma_ciecyt_id, cronograma_ciecyt_fases_fases_id) FROM stdin;
    public       ciecyt_admin    false    215   �      �	          0    16391    databasechangelog 
   TABLE DATA               �   COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
    public       ciecyt_admin    false    182   �      �	          0    16386    databasechangeloglock 
   TABLE DATA               R   COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
    public       ciecyt_admin    false    181   7      �	          0    16552    elemento 
   TABLE DATA               i   COPY public.elemento (id, elemento, elemento_formato_id, elemento_modalidad_id, descripcion) FROM stdin;
    public       ciecyt_admin    false    205   D7      �	          0    16557    elemento_proyecto 
   TABLE DATA               s   COPY public.elemento_proyecto (id, dato, elemento_proyecto_elemento_id, elemento_proyecto_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    206   �<      �	          0    16539    entidad 
   TABLE DATA               3   COPY public.entidad (id, entidad, nit) FROM stdin;
    public       ciecyt_admin    false    203   XI      �	          0    16547    entidad_financiadora 
   TABLE DATA               d   COPY public.entidad_financiadora (id, valor, aprobada, entidad_financiadora_entidad_id) FROM stdin;
    public       ciecyt_admin    false    204   �J      �	          0    16469    facultad 
   TABLE DATA               A   COPY public.facultad (id, codigo_facultad, facultad) FROM stdin;
    public       ciecyt_admin    false    192   K      �	          0    16599    fases 
   TABLE DATA               J   COPY public.fases (id, fase, notificable, fases_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    212   ;K      �	          0    16667    ficha_tecnica 
   TABLE DATA               u   COPY public.ficha_tecnica (id, titulo_profesional, titulo_postgrado, experiencia, ficha_tecnica_user_id) FROM stdin;
    public       ciecyt_admin    false    221   .L      �	          0    16562    formato 
   TABLE DATA               F   COPY public.formato (id, formato, version, codigo, fecha) FROM stdin;
    public       ciecyt_admin    false    207   "N      �	          0    16464    grupo_semillero 
   TABLE DATA               ;   COPY public.grupo_semillero (id, nombre, tipo) FROM stdin;
    public       ciecyt_admin    false    191   jN      �	          0    16513    impactos_esperados 
   TABLE DATA               u   COPY public.impactos_esperados (id, impacto, plazo, indicador, supuestos, impactos_esperado_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    199   �N      �	          0    16625    integrante_proyecto 
   TABLE DATA               �   COPY public.integrante_proyecto (id, integrante, descripcion, integrante_proyecto_user_id, integrante_proyecto_proyecto_id, integrante_proyecto_roles_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    216   �P      �	          0    16411    jhi_authority 
   TABLE DATA               -   COPY public.jhi_authority (name) FROM stdin;
    public       ciecyt_admin    false    185   S      �	          0    16431    jhi_persistent_audit_event 
   TABLE DATA               a   COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
    public       ciecyt_admin    false    187   cS      �	          0    16436    jhi_persistent_audit_evt_data 
   TABLE DATA               N   COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
    public       ciecyt_admin    false    188   �[      �	          0    16399    jhi_user 
   TABLE DATA               �   COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) FROM stdin;
    public       ciecyt_admin    false    184   �[      �	          0    16416    jhi_user_authority 
   TABLE DATA               E   COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
    public       ciecyt_admin    false    186   �_      �	          0    16456    linea_investigacion 
   TABLE DATA               w   COPY public.linea_investigacion (id, linea, codigo_linea, linea_padre_id, linea_investigacion_facultad_id) FROM stdin;
    public       ciecyt_admin    false    190   )`      �	          0    16691    menu 
   TABLE DATA               `   COPY public.menu (id, nombre, url, icono, activo, menu_padre_id, es_publico, orden) FROM stdin;
    public       ciecyt_admin    false    224   w`      �	          0    16477 	   modalidad 
   TABLE DATA               H   COPY public.modalidad (id, modalidad, modalidad_acuerdo_id) FROM stdin;
    public       ciecyt_admin    false    193   �b      �	          0    16578    pregunta 
   TABLE DATA               �   COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    209   �b      �	          0    16531    presupuesto_valor 
   TABLE DATA               �   COPY public.presupuesto_valor (id, descripcion, justificacion, cantidad, valor_unitario, especie, dinero, presupuesto_valor_rubro_id, presupuesto_valor_proyecto_id, presupuesto_valor_entidad_id) FROM stdin;
    public       ciecyt_admin    false    202    c      �	          0    16503    producto 
   TABLE DATA               0   COPY public.producto (id, producto) FROM stdin;
    public       ciecyt_admin    false    197   e      �	          0    16508    producto_proyecto 
   TABLE DATA               �   COPY public.producto_proyecto (id, aplica, descripcion, producto_proyecto_producto_id, producto_proyecto_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    198   �e      �	          0    16448    proyecto 
   TABLE DATA                 COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id) FROM stdin;
    public       ciecyt_admin    false    189   �f      �	          0    16604    proyecto_fase 
   TABLE DATA               �   COPY public.proyecto_fase (id, titulo, cumplida, fecha_cumplimiento, observaciones, proyecto_fase_fases_id, proyecto_fase_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    213   �g      �	          0    16586    proyecto_respuestas 
   TABLE DATA               �   COPY public.proyecto_respuestas (id, respuesta, observaciones, viable, proyecto_respuestas_pregunta_id, proyecto_respuestas_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    210   
i      �	          0    16495    resultados_esperados 
   TABLE DATA               x   COPY public.resultados_esperados (id, resultado, indicador, beneficiario, resultados_esperados_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    196   �i      �	          0    16651    retroalimentacion 
   TABLE DATA               �   COPY public.retroalimentacion (id, titulo, retroalimentacion, fecha_retroalimentacion, estado_retroalimentacion, estado_proyecto_fase, retroalimentacion_proyecto_fase_id, retroalimentacion_user_id) FROM stdin;
    public       ciecyt_admin    false    219   �k      �	          0    16699    rol_menu 
   TABLE DATA               �   COPY public.rol_menu (id, permitir_acceso, permitir_crear, permitir_editar, permitir_eliminar, auth_name, rol_menu_menu_id) FROM stdin;
    public       ciecyt_admin    false    225   Qm      �	          0    16594    roles_modalidad 
   TABLE DATA               g   COPY public.roles_modalidad (id, rol, cantidad, calificador, roles_modalidad_modalidad_id) FROM stdin;
    public       ciecyt_admin    false    211   �n      �	          0    16526    rubro 
   TABLE DATA               *   COPY public.rubro (id, rubro) FROM stdin;
    public       ciecyt_admin    false    201   -o      �	          0    16634 	   solicitud 
   TABLE DATA               {   COPY public.solicitud (id, estado, asunto, texto_solicitud, fecha_solicitud, solicitud_integrante_proyecto_id) FROM stdin;
    public       ciecyt_admin    false    217   �o      �	          0    16570    tipo_pregunta 
   TABLE DATA               E   COPY public.tipo_pregunta (id, tipo_pregunta, tipo_dato) FROM stdin;
    public       ciecyt_admin    false    208   xq      �	          0    16683    usuario 
   TABLE DATA               ;   COPY public.usuario (id, usuario, descripcion) FROM stdin;
    public       ciecyt_admin    false    223   �q      
           0    0    sequence_generator    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sequence_generator', 12700, true);
            public       ciecyt_admin    false    183            �           2606    16489    acuerdo acuerdo_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.acuerdo
    ADD CONSTRAINT acuerdo_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.acuerdo DROP CONSTRAINT acuerdo_pkey;
       public         ciecyt_admin    false    194            	           2606    16650 0   adjunto_proyecto_fase adjunto_proyecto_fase_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_pkey;
       public         ciecyt_admin    false    218            #	           2606    16666 8   adjunto_retroalimentacion adjunto_retroalimentacion_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_pkey;
       public         ciecyt_admin    false    220            '	           2606    16682 "   categorizacion categorizacion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT categorizacion_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT categorizacion_pkey;
       public         ciecyt_admin    false    222            �           2606    16494 *   ciclo_propedeutico ciclo_propedeutico_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT ciclo_propedeutico_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT ciclo_propedeutico_pkey;
       public         ciecyt_admin    false    195            	           2606    16624 4   cronograma_ciecyt_fases cronograma_ciecyt_fases_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_pkey;
       public         ciecyt_admin    false    215            	           2606    16619 (   cronograma_ciecyt cronograma_ciecyt_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT cronograma_ciecyt_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT cronograma_ciecyt_pkey;
       public         ciecyt_admin    false    214            �           2606    16525    cronograma cronograma_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT cronograma_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT cronograma_pkey;
       public         ciecyt_admin    false    200            �           2606    16390 0   databasechangeloglock databasechangeloglock_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.databasechangeloglock DROP CONSTRAINT databasechangeloglock_pkey;
       public         ciecyt_admin    false    181            	           2606    16556    elemento elemento_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT elemento_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.elemento DROP CONSTRAINT elemento_pkey;
       public         ciecyt_admin    false    205            	           2606    16561 (   elemento_proyecto elemento_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT elemento_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT elemento_proyecto_pkey;
       public         ciecyt_admin    false    206            	           2606    16551 .   entidad_financiadora entidad_financiadora_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT entidad_financiadora_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT entidad_financiadora_pkey;
       public         ciecyt_admin    false    204            	           2606    16546    entidad entidad_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.entidad
    ADD CONSTRAINT entidad_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.entidad DROP CONSTRAINT entidad_pkey;
       public         ciecyt_admin    false    203            �           2606    16476    facultad facultad_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.facultad DROP CONSTRAINT facultad_pkey;
       public         ciecyt_admin    false    192            	           2606    16603    fases fases_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fases_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.fases DROP CONSTRAINT fases_pkey;
       public         ciecyt_admin    false    212            %	           2606    16674     ficha_tecnica ficha_tecnica_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT ficha_tecnica_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT ficha_tecnica_pkey;
       public         ciecyt_admin    false    221            		           2606    16569    formato formato_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.formato DROP CONSTRAINT formato_pkey;
       public         ciecyt_admin    false    207            �           2606    16468 $   grupo_semillero grupo_semillero_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.grupo_semillero
    ADD CONSTRAINT grupo_semillero_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.grupo_semillero DROP CONSTRAINT grupo_semillero_pkey;
       public         ciecyt_admin    false    191            �           2606    16520 *   impactos_esperados impactos_esperados_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT impactos_esperados_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT impactos_esperados_pkey;
       public         ciecyt_admin    false    199            	           2606    16632 ,   integrante_proyecto integrante_proyecto_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT integrante_proyecto_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT integrante_proyecto_pkey;
       public         ciecyt_admin    false    216            �           2606    16415     jhi_authority jhi_authority_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);
 J   ALTER TABLE ONLY public.jhi_authority DROP CONSTRAINT jhi_authority_pkey;
       public         ciecyt_admin    false    185            �           2606    16435 :   jhi_persistent_audit_event jhi_persistent_audit_event_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);
 d   ALTER TABLE ONLY public.jhi_persistent_audit_event DROP CONSTRAINT jhi_persistent_audit_event_pkey;
       public         ciecyt_admin    false    187            �           2606    16440 @   jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);
 j   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT jhi_persistent_audit_evt_data_pkey;
       public         ciecyt_admin    false    188    188            �           2606    16420 *   jhi_user_authority jhi_user_authority_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);
 T   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT jhi_user_authority_pkey;
       public         ciecyt_admin    false    186    186            �           2606    16406    jhi_user jhi_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT jhi_user_pkey;
       public         ciecyt_admin    false    184            �           2606    16463 ,   linea_investigacion linea_investigacion_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT linea_investigacion_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT linea_investigacion_pkey;
       public         ciecyt_admin    false    190            +	           2606    16698    menu menu_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.menu DROP CONSTRAINT menu_pkey;
       public         ciecyt_admin    false    224            �           2606    16481    modalidad modalidad_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT modalidad_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT modalidad_pkey;
       public         ciecyt_admin    false    193            	           2606    16585    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public         ciecyt_admin    false    209            �           2606    16538 (   presupuesto_valor presupuesto_valor_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT presupuesto_valor_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT presupuesto_valor_pkey;
       public         ciecyt_admin    false    202            �           2606    16507    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public         ciecyt_admin    false    197            �           2606    16512 (   producto_proyecto producto_proyecto_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT producto_proyecto_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT producto_proyecto_pkey;
       public         ciecyt_admin    false    198            	           2606    16611     proyecto_fase proyecto_fase_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT proyecto_fase_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT proyecto_fase_pkey;
       public         ciecyt_admin    false    213            �           2606    16455    proyecto proyecto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public         ciecyt_admin    false    189            	           2606    16593 ,   proyecto_respuestas proyecto_respuestas_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT proyecto_respuestas_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT proyecto_respuestas_pkey;
       public         ciecyt_admin    false    210            �           2606    16502 .   resultados_esperados resultados_esperados_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT resultados_esperados_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT resultados_esperados_pkey;
       public         ciecyt_admin    false    196            !	           2606    16658 (   retroalimentacion retroalimentacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT retroalimentacion_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT retroalimentacion_pkey;
       public         ciecyt_admin    false    219            -	           2606    16703    rol_menu rol_menu_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT rol_menu_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT rol_menu_pkey;
       public         ciecyt_admin    false    225            	           2606    16598 $   roles_modalidad roles_modalidad_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT roles_modalidad_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT roles_modalidad_pkey;
       public         ciecyt_admin    false    211            �           2606    16530    rubro rubro_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rubro
    ADD CONSTRAINT rubro_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rubro DROP CONSTRAINT rubro_pkey;
       public         ciecyt_admin    false    201            	           2606    16641    solicitud solicitud_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT solicitud_pkey;
       public         ciecyt_admin    false    217            	           2606    16577     tipo_pregunta tipo_pregunta_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.tipo_pregunta DROP CONSTRAINT tipo_pregunta_pkey;
       public         ciecyt_admin    false    208            )	           2606    16690    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         ciecyt_admin    false    223            �           2606    16408    jhi_user ux_user_email 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_email;
       public         ciecyt_admin    false    184            �           2606    16410    jhi_user ux_user_login 
   CONSTRAINT     R   ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);
 @   ALTER TABLE ONLY public.jhi_user DROP CONSTRAINT ux_user_login;
       public         ciecyt_admin    false    184            �           1259    16441    idx_persistent_audit_event    INDEX     r   CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);
 .   DROP INDEX public.idx_persistent_audit_event;
       public         ciecyt_admin    false    187    187            �           1259    16442    idx_persistent_audit_evt_data    INDEX     k   CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);
 1   DROP INDEX public.idx_persistent_audit_evt_data;
       public         ciecyt_admin    false    188            W	           2606    16894 R   adjunto_proyecto_fase adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_proyecto_fase
    ADD CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id FOREIGN KEY (adjunto_proyecto_fase_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 |   ALTER TABLE ONLY public.adjunto_proyecto_fase DROP CONSTRAINT adjunto_proyecto_fase_adjunto_proyecto_fase_proyecto_fase_id;
       public       ciecyt_admin    false    213    2325    218            Z	           2606    16909 Y   adjunto_retroalimentacion adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.adjunto_retroalimentacion
    ADD CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id FOREIGN KEY (adjunto_retroalimentacion_retroalimentacion_id) REFERENCES public.retroalimentacion(id);
 �   ALTER TABLE ONLY public.adjunto_retroalimentacion DROP CONSTRAINT adjunto_retroalimentacion_adjunto_retroalimentacion_retroali_id;
       public       ciecyt_admin    false    219    220    2337            Q	           2606    16864 W   cronograma_ciecyt_fases cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id FOREIGN KEY (cronograma_ciecyt_fases_cronograma_ciecyt_id) REFERENCES public.cronograma_ciecyt(id);
 �   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT cronograma_ciecyt_fases_cronograma_ciecyt_fases_cronograma_c_id;
       public       ciecyt_admin    false    215    2327    214            .	           2606    16421 $   jhi_user_authority fk_authority_name    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);
 N   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_authority_name;
       public       ciecyt_admin    false    185    2267    186            \	           2606    16919 ;   categorizacion fk_categorizacion_categorizacion_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.categorizacion
    ADD CONSTRAINT fk_categorizacion_categorizacion_proyecto_id FOREIGN KEY (categorizacion_proyecto_id) REFERENCES public.proyecto(id);
 e   ALTER TABLE ONLY public.categorizacion DROP CONSTRAINT fk_categorizacion_categorizacion_proyecto_id;
       public       ciecyt_admin    false    189    222    2277            9	           2606    16744 F   ciclo_propedeutico fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id FOREIGN KEY (ciclo_propedeutico_acuerdo_id) REFERENCES public.acuerdo(id);
 p   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_ciclo_propedeutico_acuerdo_id;
       public       ciecyt_admin    false    195    194    2287            8	           2606    16739 5   ciclo_propedeutico fk_ciclo_propedeutico_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ciclo_propedeutico
    ADD CONSTRAINT fk_ciclo_propedeutico_modalidad_id FOREIGN KEY (modalidad_id) REFERENCES public.modalidad(id);
 _   ALTER TABLE ONLY public.ciclo_propedeutico DROP CONSTRAINT fk_ciclo_propedeutico_modalidad_id;
       public       ciecyt_admin    false    2285    195    193            P	           2606    16859 E   cronograma_ciecyt fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt
    ADD CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id FOREIGN KEY (cronograma_ciecyt_modalidad_id) REFERENCES public.modalidad(id);
 o   ALTER TABLE ONLY public.cronograma_ciecyt DROP CONSTRAINT fk_cronograma_ciecyt_cronograma_ciecyt_modalidad_id;
       public       ciecyt_admin    false    214    193    2285            R	           2606    16869 S   cronograma_ciecyt_fases fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma_ciecyt_fases
    ADD CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id FOREIGN KEY (cronograma_ciecyt_fases_fases_id) REFERENCES public.fases(id);
 }   ALTER TABLE ONLY public.cronograma_ciecyt_fases DROP CONSTRAINT fk_cronograma_ciecyt_fases_cronograma_ciecyt_fases_fases_id;
       public       ciecyt_admin    false    215    212    2323            >	           2606    16769 /   cronograma fk_cronograma_cronograma_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.cronograma
    ADD CONSTRAINT fk_cronograma_cronograma_proyecto_id FOREIGN KEY (cronograma_proyecto_id) REFERENCES public.proyecto(id);
 Y   ALTER TABLE ONLY public.cronograma DROP CONSTRAINT fk_cronograma_cronograma_proyecto_id;
       public       ciecyt_admin    false    2277    189    200            C	           2606    16794 (   elemento fk_elemento_elemento_formato_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_formato_id FOREIGN KEY (elemento_formato_id) REFERENCES public.formato(id);
 R   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_formato_id;
       public       ciecyt_admin    false    2313    207    205            D	           2606    16799 *   elemento fk_elemento_elemento_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento
    ADD CONSTRAINT fk_elemento_elemento_modalidad_id FOREIGN KEY (elemento_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.elemento DROP CONSTRAINT fk_elemento_elemento_modalidad_id;
       public       ciecyt_admin    false    2285    193    205            E	           2606    16804 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_elemento_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id FOREIGN KEY (elemento_proyecto_elemento_id) REFERENCES public.elemento(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_elemento_id;
       public       ciecyt_admin    false    206    205    2309            F	           2606    16809 D   elemento_proyecto fk_elemento_proyecto_elemento_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.elemento_proyecto
    ADD CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id FOREIGN KEY (elemento_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.elemento_proyecto DROP CONSTRAINT fk_elemento_proyecto_elemento_proyecto_proyecto_id;
       public       ciecyt_admin    false    2277    206    189            B	           2606    16789 L   entidad_financiadora fk_entidad_financiadora_entidad_financiadora_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.entidad_financiadora
    ADD CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id FOREIGN KEY (entidad_financiadora_entidad_id) REFERENCES public.entidad(id);
 v   ALTER TABLE ONLY public.entidad_financiadora DROP CONSTRAINT fk_entidad_financiadora_entidad_financiadora_entidad_id;
       public       ciecyt_admin    false    2305    203    204            0	           2606    16443 8   jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data    FK CONSTRAINT     �   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);
 b   ALTER TABLE ONLY public.jhi_persistent_audit_evt_data DROP CONSTRAINT fk_evt_pers_audit_evt_data;
       public       ciecyt_admin    false    187    2272    188            M	           2606    16844 !   fases fk_fases_fases_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.fases
    ADD CONSTRAINT fk_fases_fases_modalidad_id FOREIGN KEY (fases_modalidad_id) REFERENCES public.modalidad(id);
 K   ALTER TABLE ONLY public.fases DROP CONSTRAINT fk_fases_fases_modalidad_id;
       public       ciecyt_admin    false    212    193    2285            [	           2606    16914 4   ficha_tecnica fk_ficha_tecnica_ficha_tecnica_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_tecnica
    ADD CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id FOREIGN KEY (ficha_tecnica_user_id) REFERENCES public.jhi_user(id);
 ^   ALTER TABLE ONLY public.ficha_tecnica DROP CONSTRAINT fk_ficha_tecnica_ficha_tecnica_user_id;
       public       ciecyt_admin    false    2261    221    184            =	           2606    16764 F   impactos_esperados fk_impactos_esperados_impactos_esperado_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.impactos_esperados
    ADD CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id FOREIGN KEY (impactos_esperado_proyecto_id) REFERENCES public.proyecto(id);
 p   ALTER TABLE ONLY public.impactos_esperados DROP CONSTRAINT fk_impactos_esperados_impactos_esperado_proyecto_id;
       public       ciecyt_admin    false    199    2277    189            T	           2606    16879 J   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id FOREIGN KEY (integrante_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_proyecto_id;
       public       ciecyt_admin    false    189    216    2277            U	           2606    16884 Q   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id FOREIGN KEY (integrante_proyecto_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 {   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_roles_modalidad_id;
       public       ciecyt_admin    false    211    216    2321            S	           2606    16874 F   integrante_proyecto fk_integrante_proyecto_integrante_proyecto_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.integrante_proyecto
    ADD CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id FOREIGN KEY (integrante_proyecto_user_id) REFERENCES public.jhi_user(id);
 p   ALTER TABLE ONLY public.integrante_proyecto DROP CONSTRAINT fk_integrante_proyecto_integrante_proyecto_user_id;
       public       ciecyt_admin    false    2261    184    216            6	           2606    16729 J   linea_investigacion fk_linea_investigacion_linea_investigacion_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_investigacion_facultad_id FOREIGN KEY (linea_investigacion_facultad_id) REFERENCES public.facultad(id);
 t   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_investigacion_facultad_id;
       public       ciecyt_admin    false    190    2283    192            5	           2606    16724 9   linea_investigacion fk_linea_investigacion_linea_padre_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.linea_investigacion
    ADD CONSTRAINT fk_linea_investigacion_linea_padre_id FOREIGN KEY (linea_padre_id) REFERENCES public.linea_investigacion(id);
 c   ALTER TABLE ONLY public.linea_investigacion DROP CONSTRAINT fk_linea_investigacion_linea_padre_id;
       public       ciecyt_admin    false    190    190    2279            ]	           2606    16924    menu fk_menu_menu_padre_id    FK CONSTRAINT     ~   ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fk_menu_menu_padre_id FOREIGN KEY (menu_padre_id) REFERENCES public.menu(id);
 D   ALTER TABLE ONLY public.menu DROP CONSTRAINT fk_menu_menu_padre_id;
       public       ciecyt_admin    false    2347    224    224            7	           2606    16734 +   modalidad fk_modalidad_modalidad_acuerdo_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.modalidad
    ADD CONSTRAINT fk_modalidad_modalidad_acuerdo_id FOREIGN KEY (modalidad_acuerdo_id) REFERENCES public.acuerdo(id);
 U   ALTER TABLE ONLY public.modalidad DROP CONSTRAINT fk_modalidad_modalidad_acuerdo_id;
       public       ciecyt_admin    false    2287    194    193            H	           2606    16819 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public       ciecyt_admin    false    209    2285    193            I	           2606    16824 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public       ciecyt_admin    false    209    211    2321            G	           2606    16814 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public       ciecyt_admin    false    208    209    2315            A	           2606    16784 C   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_entidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id FOREIGN KEY (presupuesto_valor_entidad_id) REFERENCES public.entidad(id);
 m   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_entidad_id;
       public       ciecyt_admin    false    2305    203    202            @	           2606    16779 D   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id FOREIGN KEY (presupuesto_valor_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_proyecto_id;
       public       ciecyt_admin    false    2277    189    202            ?	           2606    16774 A   presupuesto_valor fk_presupuesto_valor_presupuesto_valor_rubro_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.presupuesto_valor
    ADD CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id FOREIGN KEY (presupuesto_valor_rubro_id) REFERENCES public.rubro(id);
 k   ALTER TABLE ONLY public.presupuesto_valor DROP CONSTRAINT fk_presupuesto_valor_presupuesto_valor_rubro_id;
       public       ciecyt_admin    false    2301    201    202            ;	           2606    16754 D   producto_proyecto fk_producto_proyecto_producto_proyecto_producto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id FOREIGN KEY (producto_proyecto_producto_id) REFERENCES public.producto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_producto_id;
       public       ciecyt_admin    false    198    2293    197            <	           2606    16759 D   producto_proyecto fk_producto_proyecto_producto_proyecto_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto_proyecto
    ADD CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id FOREIGN KEY (producto_proyecto_proyecto_id) REFERENCES public.proyecto(id);
 n   ALTER TABLE ONLY public.producto_proyecto DROP CONSTRAINT fk_producto_proyecto_producto_proyecto_proyecto_id;
       public       ciecyt_admin    false    2277    189    198            4	           2606    16719     proyecto fk_proyecto_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);
 J   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_facultad_id;
       public       ciecyt_admin    false    189    192    2283            N	           2606    16849 5   proyecto_fase fk_proyecto_fase_proyecto_fase_fases_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id FOREIGN KEY (proyecto_fase_fases_id) REFERENCES public.fases(id);
 _   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_fases_id;
       public       ciecyt_admin    false    213    2323    212            O	           2606    16854 8   proyecto_fase fk_proyecto_fase_proyecto_fase_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_fase
    ADD CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id FOREIGN KEY (proyecto_fase_proyecto_id) REFERENCES public.proyecto(id);
 b   ALTER TABLE ONLY public.proyecto_fase DROP CONSTRAINT fk_proyecto_fase_proyecto_fase_proyecto_id;
       public       ciecyt_admin    false    2277    189    213            2	           2606    16709 0   proyecto fk_proyecto_proyecto_grupo_semillero_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);
 Z   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id;
       public       ciecyt_admin    false    2281    189    191            1	           2606    16704 4   proyecto fk_proyecto_proyecto_linea_investigacion_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);
 ^   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id;
       public       ciecyt_admin    false    189    190    2279            3	           2606    16714 *   proyecto fk_proyecto_proyecto_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_modalidad_id;
       public       ciecyt_admin    false    189    193    2285            J	           2606    16829 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id FOREIGN KEY (proyecto_respuestas_pregunta_id) REFERENCES public.pregunta(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_pregunta_id;
       public       ciecyt_admin    false    210    209    2317            K	           2606    16834 J   proyecto_respuestas fk_proyecto_respuestas_proyecto_respuestas_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_respuestas
    ADD CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id FOREIGN KEY (proyecto_respuestas_proyecto_id) REFERENCES public.proyecto(id);
 t   ALTER TABLE ONLY public.proyecto_respuestas DROP CONSTRAINT fk_proyecto_respuestas_proyecto_respuestas_proyecto_id;
       public       ciecyt_admin    false    210    189    2277            :	           2606    16749 M   resultados_esperados fk_resultados_esperados_resultados_esperados_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.resultados_esperados
    ADD CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id FOREIGN KEY (resultados_esperados_proyecto_id) REFERENCES public.proyecto(id);
 w   ALTER TABLE ONLY public.resultados_esperados DROP CONSTRAINT fk_resultados_esperados_resultados_esperados_proyecto_id;
       public       ciecyt_admin    false    2277    196    189            X	           2606    16899 I   retroalimentacion fk_retroalimentacion_retroalimentacion_proyecto_fase_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id FOREIGN KEY (retroalimentacion_proyecto_fase_id) REFERENCES public.proyecto_fase(id);
 s   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_proyecto_fase_id;
       public       ciecyt_admin    false    219    2325    213            Y	           2606    16904 @   retroalimentacion fk_retroalimentacion_retroalimentacion_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.retroalimentacion
    ADD CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id FOREIGN KEY (retroalimentacion_user_id) REFERENCES public.jhi_user(id);
 j   ALTER TABLE ONLY public.retroalimentacion DROP CONSTRAINT fk_retroalimentacion_retroalimentacion_user_id;
       public       ciecyt_admin    false    2261    219    184            ^	           2606    16929 %   rol_menu fk_rol_menu_rol_menu_menu_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.rol_menu
    ADD CONSTRAINT fk_rol_menu_rol_menu_menu_id FOREIGN KEY (rol_menu_menu_id) REFERENCES public.menu(id);
 O   ALTER TABLE ONLY public.rol_menu DROP CONSTRAINT fk_rol_menu_rol_menu_menu_id;
       public       ciecyt_admin    false    2347    225    224            L	           2606    16839 ?   roles_modalidad fk_roles_modalidad_roles_modalidad_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_modalidad
    ADD CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id FOREIGN KEY (roles_modalidad_modalidad_id) REFERENCES public.modalidad(id);
 i   ALTER TABLE ONLY public.roles_modalidad DROP CONSTRAINT fk_roles_modalidad_roles_modalidad_modalidad_id;
       public       ciecyt_admin    false    211    193    2285            V	           2606    16889 7   solicitud fk_solicitud_solicitud_integrante_proyecto_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id FOREIGN KEY (solicitud_integrante_proyecto_id) REFERENCES public.integrante_proyecto(id);
 a   ALTER TABLE ONLY public.solicitud DROP CONSTRAINT fk_solicitud_solicitud_integrante_proyecto_id;
       public       ciecyt_admin    false    217    2331    216            /	           2606    16426    jhi_user_authority fk_user_id    FK CONSTRAINT        ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);
 G   ALTER TABLE ONLY public.jhi_user_authority DROP CONSTRAINT fk_user_id;
       public       ciecyt_admin    false    184    2261    186            �	   8   x�3450�(*MMJTpL.M-J�Wpt	�4�4442"N##]c]#�=... JNL      �	   �  x�}SKO�@>O~��W~%!�@--)��fw��Y�Z�p0��CD+�0'[���vGN�	�,�e]eU�����i0�%
=V��������!�Vk�]C��nͨ�&3y���c} ���z����7o�˲ma'*�(X��(���E��xMG�&����)�<�<u7�?�P���f�6+�Y?�c�qr���O�ZJs�v�)���Rz�y&mE�>�~2x��?�!�ѳ�Pm���9�Ɖ��G�u�"�rWD��&�M\$�}^�n�5b6�w墊xE��Y�M	[ӳ�$'1{%Xڿ���e��D��K���8eC�,M��ś���e]�]`�y�=nG��Q���5��/�%㥤1��^����>�L$vv�s�V���𜁣�T<<gv�~ͽrs�]�V�@��R���H����ۜ����C�^�i.�v�.��G�VZ*��9�s�� �U	��g�~H�xU�k��T�8�a½�_��Q��=9݉��|J����_��.�      �	     x�}��n�@E��W�\XK�Rq��A��lh���f�Ȯ����E�z�� qH�{�(�מ|05��t1_$�4����IQ]d����m�cOgؚ�Md�<����Y
Ob.�q�a���`���?O��,���XƟg7���&�,��8�Ȯ:0�g{d���v�>7>��Vܝ�h�Jh�?x��}D}I��%8=�2��#yY�Qh�Tҳ,��f���#i����AL����ĩJ�iD�r��։���n�r���-�T�����kF*�˴\��<)�F�D=�z��s�O�
�N�ݠ� �dCp66.+���W�L��;1��hxt�n���o�.`>�����0������r�0[uz؋iZ���G��PqP���S_�j4i�S!��?���+8	��v�$)K���)4	f��5��_�h��M��ν�����:v;Gc��*_-�*?7���^5kGc���k:���]��u���k=l�o���_f��ȇ/�      �	     x�=��N�0�ϛ�� ��s�AQ[����^g�Zr��vR�ӳ΁�=���\[2#��,������Xgf�uu��In���J����K����t�H��ە���%M�My1Es���v��Voͤ�O�%{Rm��^�Noہc"�/��qGnjwVB�qs��"9-��^^ˢT�c�8���xS�#��g�����LS�#,��NhpEB�j�>6Z�c	Q��";�V�mH�ݓd{�e�H��b��J�(N���UU��~�      �	   '   x�3430�(*MMJTp�L���4455�\1z\\\ �C�      �	   �   x�}��N�0Eד���wY�����ݸ�I�$�ʱA��6P԰iΙ{��ɺ��x&;����h��Ӛ������Pq�t6$�����H��e^�}�a .g?K��<�w$���K@Kc��aJv�-4B�Gj�ׅ�"�DD�rp%?^�O D#�M[|�<�ѓ�8�k�b�L�ܩN1�	��\;����}�lɾL��J��+X�F��a��pm�v ��������]UU� �r�      �	   V  x�uQKO�@>/�b� ���c�D$F4^�L�����4�[�S5����0߻o6�J>!�jŖ�4�ޠ�������92XUAЖp%�����}A�yYtf�A� �W#��C��y�|��(?�aͱ�9l�l�_��y������c�lj���0%O�-L�,'���-ņ��d�J�rv����X�%��$0�����5�����6a��Wg��F;��=��kd+u����ܱm(ϵ���AwHb�����=�!�M&P�_7$fƺ�6��i*��gݗf�$�MƂ*�����d�>��/��{fWj�`(Zt��D����q�%���I��� ���      �	   �   x�u�=o�@�g��T@ү�6�:u(k�瀕���(���CO���y��6E[�uU7U���vf�:���)��F�����U�fd��"t�w����$ m�/���؟W�Ӷ����_zWEoN=ض
�%�9C/Lg� i����9�x��UFu!ѯ��Q]D跔x4�A��q��o��H#�C��37���}��:�}�zT��+�EL��,�_$��      �	      x��]ߏ#��~n��xd�*�>�C�������=��Z���F3dﯿ�43;���nv�-K�n�?V��U�X-ě?������c����l?��v���m�����!����h�n�w�ǭ��y���~�Ϗ��R�꽄wR|���H2� 9|���~�矿��`����Ũ�D�
�tP2I�w�?�}���������|�7Oy�樂�O���ﶏ�ϝT��>�vۏ~�������~w���w|�7�w�;��{|W;��3W��]|=yJ�ۗ/o���o�m6�a�˟����o�_m5��Ưj�
��Yk������,ѵW�0��n��cm��vO������yw?)Gk-Km x+H�W�&��(PQZb!���)�2����{��7����w��������aLcF�~����p�����ѧI��1���H>j�"�A��[�XLhlynl�TQ�Tw��?nYY�c�-��ŧ�KS25�֩�[�IC�19[�R��������!���y6 ��n����y���ç��o&�I�� ���:t�E���O>>���%Z���W�f�a�-�ov�w�v~�R0(��A�+��5;��ڰ��[<h���Sp9� �;�3J��(k56c�׶�L��g�<�?>�I�� �3����g�Z��ʡp�W`1{:D�&f�]mi��:�\���0f��j�m�Ba��\76�S(B�ۏ�u�,vX���ci]PF��X!0��E��m��o��Z�}c�W�
\>���#2r�����p�4��)1 ��U �M
R��Z�����ȡ���V�6��E��.x�{��-��H���IC������JǗL��B+m�p��/�eY����@duffStr����M��@�Q:�(���i�
�3gR�E���,�7�F'�)��&P�xՔa�f���Ǌ{���	ҒVܷZky�#��IHP\qM�-�?o�o��}<,���n:��_y����ag�%���(Aԥ�@QT���l��y*�	%��vo�������̏�{��F�G��&���sR��x9�&����AI��m����_:`w������y�$�"�p����9�n��� �I�A9����"��阘s��$Gտipf�&�|�{��:���1�=M�U
� �X7�Ǭ�y�C��tZ��H]Jf�=��ް|��ym��呞#�% ��:�O���-�mr���{-Q���l��$�a^x�e���^���7�ۻ�o�w����{�٦߼��w�.���GΝ�[vSmkI�����N����oO
U$�b"O�<A�E�$\4Q���Iά��o��ǺrL��c�|��kH�������B=�Zc��v+Y3P�V��[�<f<Dy�%HH ��!���g�D=��Ӟ�LJr���Q�u���V.H�j�9mٵ���?������^л����T��˶E�0J�|�O���E���E�����5mi�4�n��*�,���W4�h���7c!͔:V9~s����i92+�(X-+�J��P���0PQ�\YAVų�4$&�2�$�� � �P�+UW[���74�`�4l��Ml���{�G��(<3���ߟ�x���f����ͳ4�w�͊��3���6FX�44"�Y�E�	��L�0�gw=bf���;��.�"��dߎ�+��������A�s84f?HN�0��	O2.2��d���݈�.RWۣ�G|g6�a�Vk���@1�a�ޓ���F��crBEGx���J[W�gs"p��*��^EY�Ji�G[;�(�
�U��UD�-��4��L/*sϪJ�;s�C��!�aP����-�9���g2ٕ��yz��r��
%�č$���.b�����b�K]R��b4��2��h�FI��j�ǲ��Zi�o��86G�#..kv�����ݦs�6��]�'[/��� �,M$�4�db�N�����٢�uڹmc� tE��]�X�l�k��_n�B�� x[W�
�hLrLn�S���Ҵ�����:[�"�"9Ϯ�u��Q4�$rv*�~hkQ�R���tZ�S,�؆tBB4���H�D���.�F�O^����:�6��u�O9��m�F
|�"�vAI��]a�?�B�EF.0�)u*�,�Vk���ْ]�D���B� ��V�֧���,���1��H�<�%Wc1TW�l(���ѕ��"��ʺG��d�)���nyz�)���d��)���r�^۽�//J<���$Lʍ!5 ���� �C�i��ujt9��b�HN�N�
�i:��hm�����Q�T0D�lZI��ʅ�BM���s[�ŋ�f��P�Z��^q��n�]�FIdv0�ƵtE�`ɤ�%��	�v�	��(&�A�b���i
|�8�q/Fʺ7��#$�e�%�PJZe�N���7K��U톎�u
���ԭ�ݠ߅TCT���F����!��*y%�'e���yQ�{B�+����lJ�M1A��ń���	��*����������^���3�6l-I&����X�`G�(R���'�h��Y���������؆<��*�4B3I�	cf�u��JD�S��h���|ώ�5|�I���ӧ���x�?��~7�����.xڢ	��:[��͎BI�z?S�㟾�y����k2\�����4��к2��Q`iC�eRliM�Ih/\��+�s2�H�e4@N���{2|������/��f�t3+� ��![�^	�ip2��R�!�)ޛm͖���r�Q࠯Ď��N0F������I�^�B	�&6���-nqE�Ѩ���.�eGT�/�������-۰ݴ)15����y�l�Vڢ2�j=Cbn��ģ�y�)oڝ-�U��� ּl}���p}�����!�]��n�	>�:�����!lS�(�X�l����H�L{�pHZ;�S� ,���:0��S�7?76[��;
��$��lA4:9f|�M�R��ޓ�0*�S�� ��^t߬�q�6$!���S�"�fx�F������(�إ��Y��cG�^oC�Z��v��۬̀G���C�6*�-I�b�2�Ud�{Ny;<��f�5"]�-��6�z���>I.c7B��1��Jb4,�l�v�]��;o�J̙FW�O6^[ϖ%q;�y�����B�������D]�fL4Lx�R22ۜ'&�Gn�����ly��v�����B�X}jڅ��H5a���D&GUL�D�䡺���ƃ�DxQa;"[aO/�����YAMAe��N�B���P���Z���a��u����d�6�䍰DJF�k������Q��#Ȏ�_4�u�c~��+��>Q�_��@��$t*��T�Yk�=O��,�@L�����M��vgKwUV���5�����z ק�+p�}�"5T 0"�TB�����n�A�-u�c>��yT���#/�/:��.�W1dv?��R[�C*HRt]������3)��SK��ɛ�r{�k��8S�s����>�äxfa����*J�,;G��Ț\LE�U1���iI^ܸ޳� 'I���+E|�`���#����Ĳ2:B�X���R9SӫݗV��n�a!Jm�����t/,,�b�"�	�ߣɬ�*ɐJa> ���O1^XEa]���zh�y^1�=h�Sk���Ӏx4�Lk���1g2$���Ԃ�(��Nm;)���Iv�w�1�+F��;�����6��JO6
������iY��"0�YuNy��� :�V�6��d��.q3X�ـT�])}��&?U������Z��
����'Ga���3����K�!%����������@�:�2�5|H����jkm_�]��u��5����+F��;��UQ[���4NUb�*
���
=�C�|��H/NU=���(r����6n��ٻ�kI��mab���}d}N"2�4k�?��6[���h��i7�+�ݡ;��G�v�m�r9`N����6�������h4sLrs���ޚ�aI������`VB �  �B\c$x�s��{��Z�w�c�\����jh��������"h�����k��`�6�T&F�����I�0�
n��v^���U�@O���~��A���դ���Z�ărR�8�}���E�~4]jME;�	ӯ/���`�6��B!�j��}�(��>�Pذ_�ׅ��w����+�'xG���|��9�m������H����0lb3��R�l2������O&Jm������k����l��������6B�1�p�!��ds��v��2j�S����LMIqN��_�̦@�#D��]ԭv�z�|
�	#Xp.� L%O�<+^�.�݋��,��Wh˞�qy���Ó�m�ki��d��@c*���
*sC@T�'���2Ċm����CcK ��R�l���b0[v�/Fc�f�Z4����^%�iO$#J-����9���v*���mM0���8W�V�D��?���KaO���tf{�<���[�L�S�p�$�4���ɖ4��F<�-��ċ����	L��A��\�V\b �X�Ȁ\0�FK
�->������+˚�c�}�ZU��\&"fޞ�Ρ��Xfhr���TX>/��I������5k^tT�=���"��\�ђ]A˰�,��3 #�d��,6YT�)�g֣}�,����w�u_&��.�|�z�SM���R"�n�UPե1C�&�3;���,]CL$( E���[�����X������z�����~�������Q��TO�t~���!�u:o�oJH��6��3{�*Kld ��L�)ձ�!c}9v������G��Q���i��gp6��V"n|Ǔ��2���^N��e��
4D&{��f��&jV�'�;r���',���sgK�S�S
�����R,SX�e������Y12�@AĜ�5���Es��8S���ƺ����CWF=�8�N�إK�
����u}Q��<�.ޥ�[��P)S+���k�^�����P|y���Ժ~y9�c2��M���s�Z%�+m9�\+����AѢH��$�W-Rf�Ξ���֏� G'��?�m����Ο�V�ӂ'4�#R9-���v݆�nlq�jp��f�B�8��
�k�Wu��L��m��ı����Ҫ��dm���w�r+SԶU�E�B��l�s�(�+JM�}����e��h��a|}�)n�Y��玖e�ϣ�KQa>�Z:&ɋ�����"=�dd��6��7H>�h�+A�ǲT�g���I�L�����{�-ig;��ud����r�����Qm�D-����Pg	Z����-g�i��۷>aE��}y���Zs����F�G�QvP���OGռ}P�V�T���>R����)#&1��y�FN:-}�/ԓ�Qg��崉c�\��o�P]�PݙŜ�A,P�g�A[�҆Z�ԅm��*�0�y4"{/���D�Yh42ўr�yA�i�斎�x�a��r���k���m6���4*J����h�ȵ�I�=d���c�zx4���}�C���%���g�(���V	3@[���Шv��'�'��ȱV�*B�X�>��_rN	��G���=��x;��.?���3��&�3>���
�?�%�e ��1�����='�?qވ N�OJ����q��^�L��}�9�D��fѻ���nm�Q)�ѐƚ��5�M�`��an4�4�~4�vz�豧���!w�����뺷%2���L/���(�U�3L��D��LT�h�@y�59���{xM=��/߼������X\8��~������ǩ�s�7��.%Gp&bb�S
�(]SE�D7�W�?��ѳnƏ��.�P�=��ǹ<�Yn�f䭺����Ɍm5]/�,�S6�}�!!R	V��յ��_Z;:i�HG�ϝ������<�BY$����8�)��-|*6GS��[b�*g�	.���i2_{r��Ju���#ӡ��4�7�8���].��DA����5lϾ`�^�6��c���y�9)�r�&5wԕy{�ѧY�`A����=Nzm���C�:��>��u��IԴ2�G�e�P)�\���g�3�?��ݾ��-���&ci"k߷�v��n���*�"h��er�B������m�=�S�㇗��:_����mg��d�z��~��GeY���#,�X�J��Qe|\��꽗ﾼٿ�v�_6_}���%���      �	      x�3�L��"�=... U�      �	   �  x�mWMo�F=˿b�6 �uj�+��H�~ I�4G_F�^c�����>��K�����,IQV�@���7oެ/^\�X|���������������][�l4���fg
�-�f�P$S����R4�Z�����x[�ɹ�E�3]�'Ok���.�ѳI���º�S�(�c�j'<'C��P�À<����D�ػVԺ-�#2�*<W�ʜ\���a�Ѧa��[[t8|Tn�@͞PцcM�럂	�):��-+��\i�+�wI2�6�~�]I�"�D�CF��M��$u��6	����?�)�4ڮ�����<.�	5łMk���
^^�Zq������L��﵅�M �X\yB ��!����`;|�UZ-��1p�����4ɳTS�:s@� BՍ�p��]$o�凚��ڮ`L�>k_��n���=����� @�-���CU8 �G�S��O	�B�ʤ�u�l\�#qn�r�RQ���A6��;'��Jz�p(m|���h�(����0(:��!��z�sI�adbB�r��Jj<
�9��}���ԭ�?��_����Io&'H�S�kX�g>�$���=�g]�\� ��	�����NDn�3�nӅ1;9P���&tV�Z�*O�K)$��R�5��<xA�LJՆ�h"E��yPn�~\�=��]����i�2=��bIyXQ~#!=큇�6k���H��q�4AX�~+�I����^]�ߠ��;65���6Қn�h%#�#��J�A��� �Ó�d���}��~%��4�� Z����4��e:dg�|�:�QI���yԨp$SC#D�j[:
�U�x�g�hr8t1ƻ�-�]f�|���'B2j�obRk����T\m[��u��� a����
�.=�߇�����]T���V4G��y�^�	|��ɜ~���5c6�E��E0D'v杭ȟ=[l���WC�jrU��W�]�\J�>�4��+e��3i��"���A��7	��dA�����{��N�!�5 X������^����9�ܪY,k!�<p��±�,�Zg4��sI��̖�Q>;�m�&�/��/��^��뗢���7TmC}G������b�l������s�=�k8a���H���s"��A�:��4�	i.��&ox���tRz���C�=�w;,Y�B�m�����ۡ��<�F�F�إ�A{�ט`���_�3�������,Kq,��fȪ\s�5�#r_s�pb��X��,����������vS�T�J���YD��B�#&���I�Nt���jV���ܑ��U���.)�r8�Kl��" ��ub'S)J,�P��E�Z��hQ~�A���#��?�Q*4�Y�$k���H0.���;�#?L� �+
�?'#�����p��?{��D�/I�?�^����<��      �	   L  x��XM�ܶ=S��'�j<���M����JI�#�9�!1C���ٝ�7:��C�9���cy�A�pe)��V��~���ͳ󫛫ꝩk���Wg˳��Źj�:��?�ޮrT_�އ�)����x�P&��&��7�6�+�+����}2�Z�	6&_]�_�Wg痧�/�p�uu�}�Rk�ǟ�Z�7�` ����F=�Ԫ`7m2ؾ��^'�{�nTc���Fu8��J��W+��֙��1���'�^�䴺ա�ʯ����Rx��CUZ��ӊLc�f�:q�Nպ�}RkL4FDm�Q)��r��E+��e����l2?e�p����8�o"w��G#���u�mPMN4�7- �������u���n��bH�N������Tl��ﹲ��a�OKh�R���#p+"^�y0am��v*�(�u��J���(��.�+x�@��9�O �b
�փ4/�)�3n
�l�-����(�+�%-@�D���ܐ��LD��=�1��
�W#��q�Dg�-B�T_�DlA���چ:w1iP.���|��Ӷ�E� �eA��PQ^!?��&	
!�)�@��:����o�ho0Cn�N����n���͐h2��Q����|J���2�8�E��d��(�2 ��eai4��ݿW-Ͼï83B�Ԓ���``Z8�X�p�M߈YY=�KN��	��z��/��?&��R�#�	���m�6�V(�X2m��
b[�k�?e��A)HV|�p�O�i�]�Nxl�e�J'�A�>����*<y����x2�<����ӦF�U������T���V��:�C"�-��n�b�_�mm5+�	|����2�
P�d��j��b�y������'���!\�N�82�(D��`hۘ��R�UwȕAN�CuF�Q�\��ޯ&�1�c�����ۖ��DMu�g	n���-���tz�"ò��L�t�9ƌ��h%�[ފ�d?xAs1Un�a7�ƏF6X!��YI�7���Ld�DA�����-d�@51��6�l�D(A������	��`��Ptmo@���>a^��AN�q0��(�u�
q9�����h��T�P��J�z+��T��j�_7��;Ԓ�$d`�ꋛ� ��E�
��  �#6ݘ�%�J�)��MY�e�&�L�4��@d�H�C�R��)���r����R���& �R�Ķ�[���Rෳ5R��m��c;9�����#�萀��d2�Z��k�"4B�����(9�
�K�	R���<�Xp.��4�ރ��8��p�+l�E��G[����`��Q'�i�tcՀ�=3��왣�nI#�5��V��1�cQ1��b�5HiV+�I���̧��1tu�|��o�.��N=�PK$J�2xر�2w�}��-�>R���<�e�|�L'������[�S��{ep��}����!�U[��� :�q�T�cVώY�n�� Sj/RF�ށP=�č��HI��Y��V'l+^)��BY�`��g��pܕ۸C��{4>��Zڊ��J453ҚиJU0p�&�Ui26M$!���[ڸC���ҋ�ܖ؂&�kv_q�/׽�8´���vQzd���Z�lh3��V��@H\����XL}z��A���-�#Y/���Α���M��T�w��!;���]N 9Dfस�,`߁ӂ	�`��:���1!�ƕFq�}��_�j�Ɏn�x緐ǖr�<�1A� ���R��>�"�>w��F�l~ޕ��4�^�Y��q��4m���M����ՊF �UR�[�؛GM�L��}�-WE��8�0��.��1�#K�4�XN����ޘ2j���t�ĭe/���LG�5�,t٥2�iip�c��N���^B+���ޗv�Y2�0m
��8]�*}x��'�(@�p<,>�/�}y�����Q?Ɨ���c�4�;^��}��4J�aB=r`1굔�WR�q���(�I�P�i�/�Ul!q�kK�P r��yBv�����S1��KwZly��,Ѹ6u*kz�/��|ؤ�ݰ�
 �1������T��/�M6����rvnԛ�������r�{k%�-��?��O!Z�r�;����w��7LJ�6�f�B2���E��6���H��	��Z\E�� �O�4~���4���(69ܩ�a4���='x-���t#��
��u� ��h�0�.�E{+�}���&��W���t1����,�װ�[�O�I_C=�|��w�/Ǐ1��g(���Q�p��@	�N��&�����`�s�K]�M����ߚ\*9 4�̎<7���9�'�g-���g�D���E��S�Η��<���;XY�$lyE��3>�$�g%>x �X/NJ�9�+ ļ�pk�\��6�e
�`�ӌ���8)�ڻ�(.F����D�^GI���0f�+����/��Z�T�� R�&�'��e�������V�;HPǳZ��kai���S�dD�4��hәf�q��9�H 1(��FZ���G37�j{�;�J,9OƖ��K�?14�4���u�LD�)ΰ��
�.�
���-�:���E�!�b���w#w&��b.z,̱��j�1J��95�ys�h�V�ʼFsB��T%���WD�@�O��2}b=�>}?�� "<���뤨H���M#o��S�/�,�(��.
)5#"�P�,K�=J�O����nO���v;K��
UG"WJ]�	�����Na���s�FgW���r[ٱ�w���	ґi�a3�YZ��:��2:�<9̮+�����%�c�W]�=HR\M�K�$��6����s�P^���y����,5m3��bvv{v)���2��}�;�f�E��cbN���C�`��l��-cm�٩�^Vo~��|���zk�C�'�\�����_݁G[��,���������/�z�4*N�v��l��A=�6���D}���˙�m�F�Z�����~[����A����zc��m�aU������*�8�����-K��0�/k�7G{��Uuw��,]W���������vSuG���m�����N��.��ճ*�7q}4�,�s�hPY�8-_b���"P����a�f�~=����|���(A[      �	   3  x�m��N�0���S�@�o���äJN\��j����$E�ӓmp�)Rd��^@#D;�
��S��٘X�,fǡs�'�B�q�R̅3��!�!;J| ����+�r��=R���K�^�$�S�
���F�&s	V]�)f�*T���ZKVop�+�DnbTWM����:�eG���%��LΣ�d�80�{Hѳ�rUM7>f7����3��C�v����I?Óz�������N�粒��P���P&�-�G�>~����V=�_/g� oU�GK���j��$ҁ��W�+8z:�\�ץR��
��      �	   Y   x�%̻�0�Z����L��	���I�w��pM-�X7	�k��B-��4�Q~�HM�0���[L��}'���9(!b��u�g��      �	   '   x�3175�4�(*MMJTHIUHKL.�)IL����� �|u      �	   �   x�-��j�0E��W�Z:}w;��nJ�W��(��q� ;��׃g��s�� 6���;-�3�9N����<B�^FL�2��=������4�[������鈣��&�@ǁU,���%EӚ�ʿ�//�<�)�O8���J������Y�{�TNv>^8a&u|ߡ�/�n�;��u�cQ�P�m�^�f�eZw���Ji7O����qa-eX�-�
��Y�e)      �	   �  x�=��v�@���S��
�4-�BCOsNW��3�-2���}z�4����|�^�����
��J���w�����R⌃Ă]b��M[�D��V�sX�	[rۆ���H�%�+p�t#�٨���4�n���ӈ��"�k�wK8ÏD�x�~����4��Xht�����K|Ӗ�����P~r)⺗L�g��?�#�l\������3�iR�l��z�юZ	RF|�X�):ƅ�ZL�fl�w��K7#��]�l�tp���I_C�������9K9�)J.����ȟ,>p�4~���W����%Lm`��*�v���D˻O��"�NL�H̅`j橳�<{<�Y�����ӑU��.��~�b
_Aw��5�{���ly�5�L����޴p6��ς�6��y,{ۋ����?n�3ڸ0}��^�A[c�&�_b��h���"��ȑ�mdQ������46vfv�V���l6����_      �	   8   x�3140�(*MMJTHIUH�/�M,��4�3�3�0�4202�50�54����� =/P      �	   )   x�3355�(*MMJTHIU(N����I-�W�L����� ��	P      �	     x�]R�n�0=3_�c{�`�I�k�]�5m���Ȍ-D�I�}��逝LK�{|�1����}�f��v���U���,K�w�[CR{��A[n�mG�WOV��u��	�_fs؛��s�ǑNۈ��t$�Y�/��Y�Rn��`8|�_���#�V���G�-��t��\,�'|ǝvVp�����~BJ��->H��=����k��b�݊�a�t�I4*2�Df�u�� �c��d�����'c�u:�w�)DX�|�g>B#�#n8)��+ظ)0�QS�<_�`�4XMg�=	^-�Ad��YJD��Z��%C�+�o�R��|�A��6�$�Չlz��K(n�~�'�P���.i�VG�ɷ�1I���BCS����SBB��iX��E��:P����y.���AM&��I*n�lg�	;��JS��i��5��l�C3�1�U�φb�D���c�7��~�"QB�%J��t�W�o�������Fq#E�W�g�콎Ο�q�l�;�*Ö;l�?]�}�2��� ��      �	   0  x�e��m$1C�w�	,Y��&RA��#s��EQ@>0|�k�#:�y�|��Yk�K$�|�-y˓�'{~��d�O�PF���`�]�c�
X�T��>�������eQ맥���]�U(���0v&�[sB3�)�t����-�緕����+�ְ��0܌݉=����0{.�O�h��T뉝���4~(�����~����:a�.6���郰%d�$5�v&9_��f��9_!���PIY3Iͼi& �?�`�$-��5��h
��-��,�
	x�a1e�$-�f��H����-�k��
�Gs��%d�ēʯqq(��PIY2��n��aʒ�Hl�Lӣ)|B%d��J6��
��%e�T�<R�&+XB�LgbY#�PlBj<薃nm�6�x�kh��ĲG�mk3��ؙX�"~M�&%�=��s��X�b?!L'cWb�����0݌݉=��P�P�`�}���7��dO��d�i�ġ��Y�W��������/���v��K��=7��[&�9XB�s�=7^l�Ŷ6�5e�r���ls�������<��b0��M���|���_��]�      �	   H   x���q�wt����
1C�]� ,�`�`(�+�1���v�wv�q�p�=]�#C l���POG�\� ��      �	   +  x�}���d���ݧ�/ ���?��`%r��̀a�؁���xƣ�iv7�.����:�J�O�����'͚�㇬Y�6�:S��ӏ�~��O�|��ˏ�~��/���/?}���UK�i$��:�K���d����y��p�n��ү�������̐�#�+��J0ٙ͘�l��-iՀ<��/�TF*y��]g�P�
HI�c�r �*�;4�d{';��o���%��-H��^�����dk�l g�Ȟ�֎�'�C��@�|�5I�I��09[mSfH3��MQ�1�3 k ����~ �U��m�=��v�kׁ���t��9��İ��!�27��RI�D�)�����ٟ�ä������JP�+�T����L�f���n����4[�<����7��f��Z��J3����������d�&A��uCJ��h�t�R�S�T-H�\^���Jɰ�.���	�4��a�)�=�mO���aߔ�d%�T]�V�j�V����B��$��ɴ�N��/9U�-�^{��H×�u<�yє��g���Z�h;a���:X�v�Ib���:�Aٳ�_��
H3ۺ���RH���6#|�$��Gv;��z�C�:s����P���:�JXV���]��{ۍE Yx*P�<������\������2H��`O��
rI�P�ɷP g ��ʰ�9���ɬhv�� ��t�eX���8E���L��	���AK��� ���d�~��ֻ��Ӱ��n�.�-l�	�d��ë��0nv�U*'���ID�OF�65�&%��!�^��-��V ������*����%jF}�(��^o��:�D�f��_S�G3����4H�u�T�zN_뉾�+:��|@�BA1u9��&QL�l���㒣y�����C��!�*i�*H�0*j����4Wp�B-�ְ�1µ���wh�Q��(lU[��Q]-$j�M��*۪�\�ٖE�i�����YE샪u7!�h�`��{�~��Q�Ԏڶ�S6x-��$6� :�������S��F��w��՟0�èh`�d���tg !��w�D�B �l�4X�]j�\��+�$�+�T�F�֕p������|�j�6�$Ow��v۵!U�}u�"���d��� �:��'U�f;����T��Qա���1���v����-e?6j֩�T�)�D]P���c�v��61"�a�I�jbG�燇��<��G���&��� ir�Wr�	�{�Y�u����jA⭀���t�QƝ����?���[	dhI�?�uF�����CAk�� M��D�ϊG�����w)"���ݹ���)s����J���n��#�k�����Av��󤡋R��l���m�}��)�|$��ei��N�V0��b����]�� �a��a^��?�V=:/�\�8tO«�*B�����qhNw���a����84�;[ۉ^��׬Y��5�G��:�߿��2Q��d�\�b˗�I�j�Ƭ���-������l5�C���}�px�J��e��07�3��'� ߽��D�^�<�X<�ńW��\V�D�+_�"������I<)P�Y���p1�~����j6=|I,K��6���u�)�Y�)~Ѯf׹�[�	�诳�1��{�W��[6���Z�����F! �Y`�c��`���;0I�2_#� x�&���YXL���%h���e�i�o�%\S���f��*�'׶�eb	���*u�K�e;e�� [��~�{M,�oR�M[��5���(�t�yϢ0���	�C���5����W�VFq�"�;#Q��>QM�����ی#V���~s�H�;��V�e�^=P��E�]�Z���f��bٶfw|��I�pV�K٢���#������J�y4�^�r<6��!Ț���u����zJ�Q��	��Kh�}?����.˂7<��_	^2�փ	nz�=,1��r�m*�?�7x��\�W@��T�bs��C^�<J�1ElF@����5B��è��2xEm�t'�:Gތ�)y�he�ڡ���}����҄������0D�F<њ���,j���1_eP����y�kd�������1Ȱ���ŕ�Wg�����-}���57      �	      x������ � �      �	   �  x���ے�:���)��ێI ����P������ ��i�b��Fm{ϸ���**�E���k�����~̽`��K�i��0n[���o.��,~����rM�9�t,�h���t���9݈ouI�,縜�3���r}~�ᡂ9����"+2���l��IS&�6g��cß���t���2ǖyG�-��N���zGqօ�E�;I<G�8L�R�S5X��0��r��m�}�r}m�Ӄά�JS��DÔ�D$�A�jt�tN� �,g$�����;M�kv���H�&�NG�,��=�+S�E�B�8ֻg��ǯk�U؊��0[��-'������#ED�n�����0�9{�P��:�V����f���T_�v��M��jd��m͇)ۄS�=a���P�sZ�>�h�Cc�̑�x�����䪐,%}��i-��K4�2�yyy+��B�׫Hl���@��o�^X�WD����W�Tj:�ߩp��u}�~]�v]u�m�F�#���X�Ǖ�?qÒ��Ϥ�#��W�I���b�v��S9ؤ�K��2��Tk���$K�P�B��Q� ��IY��(Aıb糜ލ��<�ڂ�Ch$~7�C��N)_�{;c:O�I4�����{Kp���&�����#�1v���w��b��;v���s���U����1��4�7U0��2��n.?�Q�y�9�����s>\BS3g�E v�s�f��+,��kP툹�b��	���d7�[@�Qi-��Ɔ��3p4��<�M'��k��;׳�K�a؀BC�ן��eAٙ
�0_A�r�;�H�����|�R�u��� h�����^w2�����;�`3Ԥ�T�&��2D���+�6����nM��m5�i��=�U1߀R�@���8/=��î�/�EE��}��+���<]�~���S0�\#�v�kz��AQWc�6���4��JB����$�[�x���_�+�����$-��3#?�H �A&�?,7D���������aW��T*��Y0�      �	   Z   x�3���q�wt����2�pB�]����%��$L�؆���z��\���POG�W.SS��`�`� .SsTC�1H�@�F�1z\\\ 5�-,      �	   >   x�3�00����KMTHIU((*MMJ�4���4175�25J�&)�+1�4i������ �&�      �	   	  x�}S�n�0<�_�c{p����X$A�6X��m/��u�ʒ ��YE?!?Vʯ���7���C:Ϫkأ����щ׿
�nt�?|'�P�0��C�U��xQt{��e_�0H�S>5̹mk�?ל��ဪ�<���Qpf!5sj�'���xO�2�.`�;��^���"�	�'�����R����y�WD��YV�EШ૮�5���vN�EN���n�Jk�����iIC��t�WFe��p#��v�-�Iq�.{��eFѭ�-[S�ӘK�0����h�������PM��V��g������0`�c�	]��s��d<���8x������r���Nx�-NG2�g�����!V�&'Fvn���2�n���y/l�{d֝�3�J�C�~���u�
=��^-S�鷵Y�P�����q�	5=��-�0z��3g�+�����O!�f�)��Q����[�W����[(� J ��b�!�����j q�
)�n��,����o���7cvMɣ�(���{<      �	      x�3455�I-�,V�4450����� :��      �	   R   x�3150�(*MMJTHIU((JM/�+I�t-.ITH-V(�K
¤!<��C��Ks@�r���&�@MM9MLL�b���� �J�      �	   �  x�M��n!E�5_�8��X�mEY�Q�;o����0J<_�'q[B,��S\7�_Ryf�y��2�SK�H�d�~��	���^Wd�[=caw����ʀ��8�<xڝ������I��3��&�@;%,킞Xn�n+�[����x�meej�,���|��.�5��G�����S�
������� �����5���_�8`Ogd��εD�~D%@���E���#-8C��f`"�+,�M��#��^OmFv��+�F*N=��H��^���Q,����I����K���R�ϯ��0: U�SF�=�sd��x�N;�A+�4.�����|꣮d�|4���S�Ƭv e���R������%,3v�i���nb[��ҁ�ʉ�̤a�S"���[v]ۂ���S��:%���%CBP���AjO�y
�;�"r?3�1���x<�4�K@����u
�e@�_B0�CNaS�{��?�v�?�D�      �	   �   x���n�0Eg�+�)������4ݺ��E@�
�__u�ӹ��a<��a�pn9T��dYv�v}��9m��o�XR����*>�(��Fq˼j ����Wk9�T���]�ٸD|���.�>�gM��բ$��Jq�N�gB�/�V.�R_a:Mo4�63�ւ�{�E�gw���"��lH�      �	   �   x�5�M� ����@m�_�Fwc�n(��H���BO��@B¼�f^ޔYۛ��	�}"(^K�'�\�hB	���+*��� 4N��3������Ԛ�X�Y�ܐl�mR[���&	��d�	�7N�3d�=�s����H~�fr ��ѻ��>�P�/��,W�mz��9k>Q[�	�[�i�(���T�      �	   �   x���M
�0F��9AqƉ?��	܈mEh�$���7ݔn
��D\<C�9�)�s�y�k:��<�k���a�n�!��O�8/���_OkKy�%qք����*��,������ymf�4i�"e�20ؚ�6)�eI	�0&� ��o��VoUJ�.�[�aLP�d�dc��1�dB��g&]�O}���c������aL66&Ҿ�_�	��(�'����      �	   g  x�]�ݎ1���S��������"�E�Y�\p�&ƚ�9��ӯ��T�M��8��Fc��q�R��pW��a:����Ρ��SMʰ_�M�QRVq�W;\ƨ^�C�<�j��H�'q�n38j<(uO�*�Q�y�*q�!�,qͮ��s���X��NB�ÄC2�z�g�b첄�&�`���r/�2�s���%��Ϡ���	��|2��J`\���*����?��%��^�?���K�f�v�-g�3v�G:y��+���$-�����˚D�
^���ڱʌ��m.m�b�����*��t�x�z)��1�Ċ�����R���$��mٓ��d϶7�a���M��2������k      �	   �   x�M��j�0@�����u�=�����+���j+� ���t��Ü�v��6j���|��U�Zթ�~y�M��a�6�2a��{=29�ɟ\go��)zKfQ)��w�?�����b�tmL���=r�Ⱦ{��Zmɂ2+|��q�%�O8��c��g#3_�@r���Ƨ������K����o���l���xs��F�I�w�W8^UU��xoO      �	   �  x�=Q�n1<�_�H��ݣ�H�nj�I@.\���J�r���R��'I�83��v7YI�˹���I���O\�{�_�rJ!��
F��s��&���rN��ΰ�}O
��BPn�6�׮��9� F.65I>
����a�1���\��(8N�<R`��`޸;Ljl��N��ǈ��(���|�b�PfG���YAS�E�A�u����SR1��p�<�b[J����	MU�\���d�W����S���$��@߹.��:ؼ� G����<b��i�@d���6��6���=����r"-�S����뤀�e�u)K0j+*��$o:������cm�ݞgY23k"���*�c��kFXm}0;�O���݈Gk!�;7���K�5.m�`&}�&6tD?���P��iŝ�\x�\E�Ӌ�j����Q      �	   �  x�uR�n�0>3O��`+Nl�n�rH7 ݭ�b-���I���.�C�@	����9�C����Zn#���>(�o 2�m3�ͪ۲yU��s:w70�������[DU�݂�%���9<�1*|b�;MHVbT����uҭ�c0��Pl��:���:�6��\g^d�XXh���l���p%�ꢪ�D�ʍ��^�ĿBW�:��~�d�~��m|2�
I/����ѻw�бeO�����D3~�n��
��PU��S�m�IG%=Md���i-������ff�2���(I9x�8��φ������&3�Y[dy�/�k�:[�:譛ˎ���x?�}��hJ{^�Q��Ce��g�r6A.�10�_S]��.ʏ	�~�l6^kκ      �	   c  x�}��n�0Eg�c
��,g��c+Э_��G�P�ׄ�CZ�����M�ӏ?�����qۮS��3/����Y�[)UY<Ύ���e�ҪA���C�:e����s�P�̞�f��Z�b	1m-Ćbڊ���8g�3��eO�Clh��$,!"�� 6Tm�
�!��	)��KPh<����n.�!�� 4�pU��-���и����%lH<
AC��τ��[���X
�A^����y�f�*�Y
���>�0�u0cf�`A,��(Ð�(�p&���v�?N�������$���*̢pVa.E�
3*-�0��dfWzVa�eݯ�r�*,m+Y��m�Ӹm���j�w��z����m��      �	   Y   x�3151��*-JL��4�,�4455�2407��*��*�e&&e�d�$�p�Ԙ��r:���9]�KJS2�JR�FBeb���� �\�      �	   �   x���N1�k�S� ᯼�!R����F��u��/��c��7�F�s>_���/b�n�o���C]X4�ρ����_qA���&Q�h�2����Ae(c,g�Dwn	6�l��O��X�eh�{W6����G�bqA�? E���1u��,���Nh!��[�2�)�ҵѓ�.�e�������ǰA�㊈� �%N�      �	   l  x�]��N�0�י��p4��s1HÒ��xZkҤr����ED������,�:�#{���b䚒'�~�	a�S�#����r��_�W�k���4Î�sG��Oŵ9P��YQ���D%+N���SiX^�Z��J'�kwt;S����$��L��B���+V��0$������U$�@��P
������0j/9�p� j�K˪��F��	��bv��X��Ua�]��p<Y`�}���|a�����ϽnM�O^(�X$P���6�O#�e��n��!'X�'�ݍ�[����P��m܅�	�{r��vC�O��r-�E{˷�¾8j+AO㗱:��K�v����f�7�@      �	   2   x�3160�(*MMJT(�,�WHIU((JM/�+I�,@OI,������ �C      �	   '  x�e��N�@Eמ���x?�m�P�j�wb��x43I�w+/||��5��[׸�~j:���V<�R���S���h�p�uH��K�|	�s�e(�u�p(��Y�5G�R�"	�J�����8Q��`E���3�u0nj���!2CK���y�5�{�S��,?vU�T2�q�(���=@�̆
�"��X�j��a }4	����جq-$Y�{S�t�Hә��Z��Kl�p��4�'XtZ��'��$��=Ü�����^�ō�l���
bҢ^;��8kϰ;v�jO���9�冐�     