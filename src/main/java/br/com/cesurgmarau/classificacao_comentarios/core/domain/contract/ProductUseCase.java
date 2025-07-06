package br.com.cesurgmarau.classificacao_comentarios.core.domain.contract;

import br.com.cesurgmarau.classificacao_comentarios.core.domain.entity.Product;

import java.util.List;

public interface ProductUseCase {
    public Product create(Product product);
    public Product update(int productID, Product product);
    public void delete(int productID);
    public Product getByID(int productID);
    public List<Product> fetch();
}
