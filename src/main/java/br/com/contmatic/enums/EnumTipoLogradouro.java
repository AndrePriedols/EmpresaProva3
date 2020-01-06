package br.com.contmatic.enums;

public enum EnumTipoLogradouro {
    
    AEROPORTO (1, "Aeroporto"),
    ALAMEDA (2, "Alameda"),
    AREA (3, "Área"),
    AVENIDA (4, "Avenida"),
    CAMPO (5, "Campo"),
    CHACARA (6, "Chácara"),
    COLONIA (7, "Colônia"),
    CONDOMINIO (8, "Condomínio"),
    CONJUNTO (9, "Conjunto"),
    DISTRITO (10, "Distrito"),
    ESPLANADA (11, "Esplanada"),
    ESTACAO (12, "Estação"),
    ESTRADA (13, "Estrada"),
    FAVELA (14, "Favela"),
    FEIRA (15, "Feira"),
    JARDIM (16, "Jardim"),
    LADEIRA (17, "Ladeira"),
    LAGO (18, "Lago"),
    LAGOA (19, "Lagoa"),
    LARGO (20, "Largo"),
    LOTEAMENTO (21, "Loteamento"),
    MORRO (22, "Morro"),
    NUCLEO (23, "Núcleo"),
    PARQUE (24, "Parque"),
    PASSARELA (25, "Passarela"),
    PATIO (26, "Pátio"),
    PRACA (27, "Praça"),
    QUADRA (28, "Quadra"),
    RECANTO (29, "Recanto"),
    RESIDENCIAL (30, "Residencial"),
    RODOVIA (31, "Rodovia"),
    RUA (32, "Rua"),
    SETOR (33, "Setor"),
    SITIO (34, "Sítio"),
    TRAVESSA (35, "Travessa"),
    TRECHO (36, "Trecho"),
    TREVO (37, "Trevo"),
    VALE (38, "Vale"),
    VEREDA (39, "Vereda"),
    VIA (40, "Via"),
    VIADUTO (41, "Viaduto"),
    VIELA (42, "Viela"),
    VILA (43, "Vila");
    
    private int id;    
    private String descricao;
    
    private EnumTipoLogradouro (int id, String descricao) {  
        this.id = id;
        this.descricao = descricao;
    }

}
