package br.com.cesurgmarau.classificacao_comentarios.core.domain.contract;

import br.com.cesurgmarau.classificacao_comentarios.core.domain.entity.Classification;

import java.util.List;

public interface ClassificationUseCase {
    public Classification create(Classification classification);
    public Classification update(int classificationID, Classification classification);
    public void delete(int classificationID);
    public Classification getByID(int classificationID);
    public List<Classification> fetch();
}
