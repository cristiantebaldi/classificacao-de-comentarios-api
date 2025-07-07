package br.com.cesurgmarau.classificacao_comentarios.core.domain.contract;

import br.com.cesurgmarau.classificacao_comentarios.core.domain.entity.Score;

import java.util.List;

public interface ScoreRepository {
    public List<Score> fetch();
    public Score getByName(String name);
}
