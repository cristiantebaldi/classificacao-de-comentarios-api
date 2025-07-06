package br.com.cesurgmarau.classificacao_comentarios.core.domain.contract;

public interface MaritacaAIGateway {
    public String commentAssess(String comment);
    public String commentScore(String comment);
}
