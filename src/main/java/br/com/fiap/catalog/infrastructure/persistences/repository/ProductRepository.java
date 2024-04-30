package br.com.fiap.catalog.infrastructure.persistences.repository;

import br.com.fiap.catalog.infrastructure.persistences.entity.ProductEntity;
import br.com.fiap.catalog.main.Enum.CategoryEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(CategoryEnum category);
    List<ProductEntity> findAllByIdIn(List<Long> ids);
    List<ProductEntity> findAllByOrderByCategoryAscNameAsc();
}
