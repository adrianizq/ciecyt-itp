PGDMP                          y         	   ciecyt_db    11.6    11.6     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    52041 	   ciecyt_db    DATABASE     �   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';
    DROP DATABASE ciecyt_db;
             postgres    false            �            1259    52204    proyecto    TABLE     �  CREATE TABLE public.proyecto (
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
    recomendaciones text,
    viabilidad text
);
    DROP TABLE public.proyecto;
       public         ciecyt_admin    false            �          0    52204    proyecto 
   TABLE DATA               �  COPY public.proyecto (id, titulo, url, lugar_ejecucion, duracion, fecha_ini, fecha_fin, contrapartida_pesos, contrapartida_especie, palabras_clave, convocatoria, proyecto_linea_investigacion_id, proyecto_grupo_semillero_id, proyecto_modalidad_id, facultad_id, sub_linea_linea_investigacion_id, tipo, referencias, proyecto_programa_id, programa, departamento, municipio, viable, enviado, fecha_envio_propuesta, fecha_envio_proyecto, nota, conclusion, recomendaciones, viabilidad) FROM stdin;
    public       ciecyt_admin    false    230          +           2606    52333    proyecto proyecto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public         ciecyt_admin    false    230            ,           2606    52528     proyecto fk_proyecto_facultad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_facultad_id FOREIGN KEY (facultad_id) REFERENCES public.facultad(id);
 J   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_facultad_id;
       public       ciecyt_admin    false    230            -           2606    52543 0   proyecto fk_proyecto_proyecto_grupo_semillero_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id FOREIGN KEY (proyecto_grupo_semillero_id) REFERENCES public.grupo_semillero(id);
 Z   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_grupo_semillero_id;
       public       ciecyt_admin    false    230            .           2606    52548 4   proyecto fk_proyecto_proyecto_linea_investigacion_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id FOREIGN KEY (proyecto_linea_investigacion_id) REFERENCES public.linea_investigacion(id);
 ^   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_linea_investigacion_id;
       public       ciecyt_admin    false    230            /           2606    52553 *   proyecto fk_proyecto_proyecto_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_proyecto_proyecto_modalidad_id FOREIGN KEY (proyecto_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_proyecto_proyecto_modalidad_id;
       public       ciecyt_admin    false    230            �     x��R�J�0��>E^��$m\[QT�\��3�Nk`7Y��V���|�;]�OV$	9If�sΤ������A�-���uO0�f���OFhpƼq��i�]��P�F}��n�S̵��z��n�wx{|�R�g:yɘ\$�K�	�I1@�q��rx�ڦM���%v��DR�U���n��y�!�֡r,���vh��@$�_"�!l�~3SYU�L�F{Я�6�Q;��v�9�����&�E��D�Q@���q�>��B��=Zm����p����H�U�$����"�ݑ��'�ק�gGi�+�b�
C4Q;���=xdh����������2|C��uЄ���-\�B��3c*L�ZTuY��x�1�&y���	��k     