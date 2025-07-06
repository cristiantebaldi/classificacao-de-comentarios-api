package br.com.cesurgmarau.classificacao_comentarios.core.domain.dto.review.report;

public record TotalReviewsByClassification(
        String classification,
        long total
) {
}
