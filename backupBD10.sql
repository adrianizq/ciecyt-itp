PGDMP     *                     y         	   ciecyt_db    11.6    11.6 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    52041 	   ciecyt_db    DATABASE     �   CREATE DATABASE ciecyt_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';
    DROP DATABASE ciecyt_db;
             postgres    false            �            1259    52186    pregunta    TABLE     �  CREATE TABLE public.pregunta (
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
    puntaje_maximo double precision,
    pregunta_fase_id bigint,
    pregunta_fase text
);
    DROP TABLE public.pregunta;
       public         ciecyt_admin    false            �          0    52186    pregunta 
   TABLE DATA               �   COPY public.pregunta (id, encabezado, descripcion, pregunta, pregunta_tipo_pregunta_id, pregunta_modalidad_id, pregunta_roles_modalidad_id, pregunta_elemento, pregunta_elemento_id, puntaje, puntaje_maximo, pregunta_fase_id, pregunta_fase) FROM stdin;
    public       ciecyt_admin    false    226   e       +           2606    52323    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public         ciecyt_admin    false    226            ,           2606    52488 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public       ciecyt_admin    false    226            -           2606    52493 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public       ciecyt_admin    false    226            .           2606    52498 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public       ciecyt_admin    false    226            �   V  x��Xˎ�]WE.g$l�݀f��B�^F��l�UiO�ʕ�|X4Ò�Q�f�D�؜�̬G��ۢ��zdF�8q"��O�������R���m��|-���PN�*���F�f����U����>XY�Ot{/w��������j/�����P����*�ӕ��z0�˦���]��E�����X�׋b�^-�o��r���Ŝ����l��X��oM���|����\��תQ"4��+�>�;C��m����	����ɕ��0rk,S}�+(ᔰJ������	/���n,�O��hy�[���y���Zy}4��_9�~n�Q7e�ee�/��j��gC�J�q3Q���4Mi�7d�^U:�Բ�V&/	kC���g��o�!ÿ<��b?Y�*~��4��;�UVQDc�)w0�H���+��#N�P�^��Vc��J�t?�+��.x�z�( �E�.9���AY����E�����'=��fM��>�(~x��طZl�a:3ftij��h��{�{zݺ�Mصٵ_fW���S��AK7	$�#�9����6q��O��`d�>�q��5tM/�w����R6 �	����]tD"�,��c���GFp*
6�j�e��dR'v��P{&4��p�ϴg׺K�g������o��B���Q�����3�Z~E��z�]l�"nou�A�aʥt�+h��w��W�����8b7^)D���6�RV�=�����-�@�iL�����3D��P ����	L���=ȭN��H9�>��ݟ�wD�+cAF����*<� KЍ~Ql-E��3��#U�XT*O��f���R�?�Tx5����>�\d���Z2�	]�yh�]�:��O�)����D�a,B���	�ðC &u�>�	�\�'�N�ah����� ټ�μ\-���7��sj�R��G��C)#LCBHn±�US���t�����R5���Vӹ��J�xL�OT)���e��t�q���u��x��z���ⅅ{;+c{�,SAV�S�,4d� q��m��8��N���+�5um�>`�c�U�}�+))���5���!�A�:<��zJJTf��|~V��։���k �~���+� ���G�]9q����]�VٌW��l�D���p�+%�,v���L�)�M���ڣ��� �l�60Qb�s���X&P��$dJ�����\0�a�r1G� �b&P&��M�;�Q����R*�$>Pa��w���HǦ�g��2xΙ��TA�F�U]"Юݎ��Ţ��%5�#����m˚��f�ϝ�N�1P{٨kӵq��)#��X5]�f�;�̰��fZ�]��Ū2�bQ�h���SN濳���}�Շ��A�Ԧ���5lK��"�'*i�����EK.��JP�F�X�R�e|~�V�X\���sB�W������Ӓ�%f�Ku%�9�CG�8�좤�5W��M#[Gd��X����\��h��DS����W�dO)L2ȅM�{Ƃ;]�_cI���A��덏Ma�����A��Ų���)	MZR�Fy1@_(�s���5*R�����GZ�1V�k�"j
׉���0�K�ث��A�S01����c��|xj�#i��`'ˣ�;H������n��GBP'�H
�'�8b��7FŲ�sR�srV1�i(�1`oz��F�c63iڢx���t>��<3�/���[�d��������_z�1�@�4&%1/�����\���=����)'��veE��-��<G�ܡT��C�@��Nm:9kɅ���u�(���3��9�Iv��9#5<�w`�i�S�������odF
P�ƩU�|I���:�;�s�]ށ5���u �Z��$�l/�0o�%w�;�٦�D�|���z��0yHe�Q���M����U�u��	�N��'!CW���2��Ԙ��i���mBr�8V�geR��f�$>��kČ�l�\O8=�~'��:O���t=u�#~���L���0��|z�8�F�����j�N�RA�'B��({��ZeE�"LDG{T��3!�x�e�����蛞4y��C�蘯쌧	�<�o�y��(�h8vǇ)r�Rف�	���;u��'�������}�(     