PGDMP     &                    |            pessoas    11.22    11.22 "    !           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            "           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            #           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            $           1262    24610    pessoas    DATABASE     �   CREATE DATABASE pessoas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE pessoas;
             postgres    false            �            1259    24622    departamento    TABLE     �   CREATE TABLE public.departamento (
    id bigint NOT NULL,
    descricao character varying(255),
    status bigint,
    departamento_id bigint,
    pessoa_id bigint
);
     DROP TABLE public.departamento;
       public         postgres    false            �            1259    24620    departamento_id_seq    SEQUENCE     �   CREATE SEQUENCE public.departamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.departamento_id_seq;
       public       postgres    false    197            %           0    0    departamento_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.departamento_id_seq OWNED BY public.departamento.id;
            public       postgres    false    196            �            1259    24680    departamento_seq    SEQUENCE     z   CREATE SEQUENCE public.departamento_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.departamento_seq;
       public       postgres    false            �            1259    24694    pessoa    TABLE     �   CREATE TABLE public.pessoa (
    id bigint NOT NULL,
    nome character varying(255),
    status bigint DEFAULT 1,
    departamento_id bigint
);
    DROP TABLE public.pessoa;
       public         postgres    false            �            1259    24692    pessoa_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pessoa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.pessoa_id_seq;
       public       postgres    false    203            &           0    0    pessoa_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.pessoa_id_seq OWNED BY public.pessoa.id;
            public       postgres    false    202            �            1259    24667 
   pessoa_seq    SEQUENCE     t   CREATE SEQUENCE public.pessoa_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.pessoa_seq;
       public       postgres    false            �            1259    24630    tarefa    TABLE       CREATE TABLE public.tarefa (
    id bigint NOT NULL,
    titulo character varying(255),
    descricao character varying(255),
    prazo timestamp(6) without time zone,
    duracao double precision,
    finalizado boolean,
    departamento_id bigint,
    pessoa_id bigint
);
    DROP TABLE public.tarefa;
       public         postgres    false            �            1259    24628    taferas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.taferas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.taferas_id_seq;
       public       postgres    false    199            '           0    0    taferas_id_seq    SEQUENCE OWNED BY     @   ALTER SEQUENCE public.taferas_id_seq OWNED BY public.tarefa.id;
            public       postgres    false    198            �            1259    24731 
   tarefa_seq    SEQUENCE     t   CREATE SEQUENCE public.tarefa_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.tarefa_seq;
       public       postgres    false            �
           2604    24669    departamento id    DEFAULT     r   ALTER TABLE ONLY public.departamento ALTER COLUMN id SET DEFAULT nextval('public.departamento_id_seq'::regclass);
 >   ALTER TABLE public.departamento ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    196    197            �
           2604    24701 	   pessoa id    DEFAULT     f   ALTER TABLE ONLY public.pessoa ALTER COLUMN id SET DEFAULT nextval('public.pessoa_id_seq'::regclass);
 8   ALTER TABLE public.pessoa ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    24719 	   tarefa id    DEFAULT     g   ALTER TABLE ONLY public.tarefa ALTER COLUMN id SET DEFAULT nextval('public.taferas_id_seq'::regclass);
 8   ALTER TABLE public.tarefa ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    199    198    199                      0    24622    departamento 
   TABLE DATA               Y   COPY public.departamento (id, descricao, status, departamento_id, pessoa_id) FROM stdin;
    public       postgres    false    197   $                 0    24694    pessoa 
   TABLE DATA               C   COPY public.pessoa (id, nome, status, departamento_id) FROM stdin;
    public       postgres    false    203   t$                 0    24630    tarefa 
   TABLE DATA               o   COPY public.tarefa (id, titulo, descricao, prazo, duracao, finalizado, departamento_id, pessoa_id) FROM stdin;
    public       postgres    false    199   C%       (           0    0    departamento_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.departamento_id_seq', 5, true);
            public       postgres    false    196            )           0    0    departamento_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.departamento_seq', 1, false);
            public       postgres    false    201            *           0    0    pessoa_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.pessoa_id_seq', 4, true);
            public       postgres    false    202            +           0    0 
   pessoa_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.pessoa_seq', 1551, true);
            public       postgres    false    200            ,           0    0    taferas_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.taferas_id_seq', 5, true);
            public       postgres    false    198            -           0    0 
   tarefa_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.tarefa_seq', 901, true);
            public       postgres    false    204            �
           2606    24671    departamento departamento_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_pkey;
       public         postgres    false    197            �
           2606    24703    pessoa pessoa_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_pkey;
       public         postgres    false    203            �
           2606    24721    tarefa taferas_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT taferas_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY public.tarefa DROP CONSTRAINT taferas_pkey;
       public         postgres    false    199            �
           2606    24747 "   tarefa fk8gdqvyn8q10lycnjutjtykl07    FK CONSTRAINT     �   ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT fk8gdqvyn8q10lycnjutjtykl07 FOREIGN KEY (departamento_id) REFERENCES public.departamento(id);
 L   ALTER TABLE ONLY public.tarefa DROP CONSTRAINT fk8gdqvyn8q10lycnjutjtykl07;
       public       postgres    false    2709    199    197            �
           2606    24713 "   pessoa fk9aghxdvle8vwbvms12rau5fix    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT fk9aghxdvle8vwbvms12rau5fix FOREIGN KEY (departamento_id) REFERENCES public.departamento(id);
 L   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT fk9aghxdvle8vwbvms12rau5fix;
       public       postgres    false    203    2709    197            �
           2606    24752 "   tarefa fkgi2ns86q4vnvytix0k0pbdwhj    FK CONSTRAINT     �   ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT fkgi2ns86q4vnvytix0k0pbdwhj FOREIGN KEY (pessoa_id) REFERENCES public.pessoa(id);
 L   ALTER TABLE ONLY public.tarefa DROP CONSTRAINT fkgi2ns86q4vnvytix0k0pbdwhj;
       public       postgres    false    203    2713    199               F   x�3���4��".#N�����b����Ԥ�"��1L" 1=!l�-(J,���r:�%e&�b���� =3�         �   x�u�=� �g8'�0���A������C�(�����D�x�Y�zލS�@�S۸n�yM�y	qZ��������>O�0M�����@ I���
d�EL��Du+g�r6��p�T"���k��;������V�^R�ͅp1���;0'�$�T��'O�R��S0��B���EQ��v�i�,)�         �  x���Kn1�ךS�(��k�mN��b+A�z�(�\)��ʙik-R�\��G���q�j�oS]�zW�T����� ݁��3���8��HQ5)T�q v���Ǧ?ʏ�5�C�_�M�ˋ���W]�?��)!ې�*0	��3Eʃ�*x^�6�5�5H�9���t�w	X��5��`f��O>��	�_��<Nd�u[kW������Yti����y'���٦�o�!�'eWI���ˬ@۽OM�q[���W�C�"ې��7�(^A��&$�[x��ծ��h���$�V���)Q̷8����?'Q���_�:�>~o��L���E#f�F�������7˘r��p�Ƣ��J#62ə��²2��Tm��L�L���dɆxSo��@
��G���1�0��.8�     