package br.com.cesurgmarau.classificacao_comentarios.core.domain.dto.review.report;

public record TotalReviewsByAccount(
        String account,
        long total
) {
}
