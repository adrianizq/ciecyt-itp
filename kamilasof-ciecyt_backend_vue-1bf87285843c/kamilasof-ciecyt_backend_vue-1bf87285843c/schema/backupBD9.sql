PGDMP     #    %                 y         	   ciecyt_db    11.6    11.6 
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
    public       ciecyt_admin    false    226   e       *           2606    52323    pregunta pregunta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pkey;
       public         ciecyt_admin    false    226            +           2606    52488 *   pregunta fk_pregunta_pregunta_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_modalidad_id FOREIGN KEY (pregunta_modalidad_id) REFERENCES public.modalidad(id);
 T   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_modalidad_id;
       public       ciecyt_admin    false    226            ,           2606    52493 0   pregunta fk_pregunta_pregunta_roles_modalidad_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id FOREIGN KEY (pregunta_roles_modalidad_id) REFERENCES public.roles_modalidad(id);
 Z   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_roles_modalidad_id;
       public       ciecyt_admin    false    226            -           2606    52498 .   pregunta fk_pregunta_pregunta_tipo_pregunta_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id FOREIGN KEY (pregunta_tipo_pregunta_id) REFERENCES public.tipo_pregunta(id);
 X   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT fk_pregunta_pregunta_tipo_pregunta_id;
       public       ciecyt_admin    false    226            �   B  x��X�n�]�_�� �"eY��p��I���l�=-����I�a�o��@�lh~잪�y��
6�ywթS�Ns�^��>���&��}�;�/u>y�:�\����UƇZ����+*:)k�2KOT�W����Q٨l���6��c���	:�*����V����+��n�����u�s\��E���BQ���p������������pt�v:8���Mc�:h:����J����m�q�}�J�V��l��?����_g�����}�\^���2s�v�=>�-M��Y��JФ�b��窶�N�� �@�N��P�RU:��־ֹ�t6�� }5��_�����1�+�y|x���*W��Ҫ��%�	��;���Z��ƇL�9򈊨`,�l)5���lIǜ�U�"��:�_��S���b9��Ʀ���C��ږ���*���-@��h;���(+���sEu�����\)�p�	,���-���d��ι�:VDψ<r�Ӝ*a�m�-�
����sU���������x+6y�zE��L̉���@���_��9-��]�˿�Jr�C�mJ���=x�w^���A����~�v�~�+}j;�aj��5e���y|��|�$���RhL�g�*Ӡ�X�v�@I��|��N'8B�K� ZA������g�����.�J�5rW�^̌��9�>*'�Hi�� �y�m�ø5�买루�g�%ި�
9R�������1`��31���̠��}���z�C�����k��M�
��R�.V>:�	�]����:�;�6χ�aIPEo�Z!nExGu�J�!}���'p�Y�}��G� Ë#W�sQ]���7�� )�f�8�(�ſ���K����BE��}������4�^A�F�z��Y���T_���������nol��h���%z�Kc+*�J�D��V����U�?D�V���d�N6�9E�b'"d���a
�Rĝ���5Θ�,�r�*�5X[������$zH��נ7<���wJ�m�8�S2=2��I�� �jp�W������b���]�5��ftJ�4I�M��t�����M�/-�ߔ�(�Đ�l���j������b�� G�= 
��J��<�ȼ�k҉?����e6O�d�E%x�6srw$�����=�E�~�OD~q�j��LwL�ʗ�"!�o��%B8������rT�����4�'������C%&#h]���Af-���˓�-�����5YKzOD*9�\��`&P��fL�Ql��t�G�ATF�%IDNs��4n��!��-֩���1q�m��v$��3�7���Y@ٽ���}���Z;ց����Oc�ٞu����SU�������mD�zKFC�f�)��d�ֽ�\\�F�Ӿ���ec���ů�:�(o�b��{-�L�_�ˇ�ëa��7�"����Ы���Fg�ׂǇ�pe|���D:� ����㝜�@nuD��ԣݸ:<�r�ݏ�dDpl,>�^R�10�FgT��k㳣���
aC�԰�o�A���S�x�۲�?[�I�Nf�%cM�s�O�v����!�D��zLKڪ�`,���ۻ=�O{����`ܧ▿{Q���-*��]��uj�m����)�=&��RG�����<�D	�\5�[�q4I��y[�ԄFOl�5�I-'Ļ����s���1����m��_u��48�6����a_.���$A���oGd����Km���L��b�qW����j0Ȣ�*k z��B���X��M.��ttEG�2�`E�H�:�<-痳a�F�Lv �/l�g����O�����_�d
��W6�_I?����P{�c�J�~�	�y>WM������ΰ˅��,!����"}�Æ&Bc�@SV<2�//�+�B����.�	�H0��:�لԻ#o�%�z�1��@b��R�p��G�@;��?���jdΝ����2
ò�h�~�'q��lv��:r]�����*&?���$��sF
U����~��ާf�;I׹��岩
�.�~ډ]؉��L����EI�O%9���ٕ�~$;2f�5Af���̼'di��I�:��v�PɌLAcj�v9����(�?�?{���W�g     