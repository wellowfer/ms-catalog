package br.com.fiap.catalog.infrastructure.controllers;

import br.com.fiap.catalog.application.usecase.ProductInteractor;
import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.exception.CatalogException;
import br.com.fiap.catalog.infrastructure.controllers.DTO.product.CreateProductRequest;
import br.com.fiap.catalog.infrastructure.controllers.DTO.product.ProductResponse;
import br.com.fiap.catalog.infrastructure.controllers.DTO.product.UpdateProductRequest;
import br.com.fiap.catalog.infrastructure.controllers.mappers.ProductDTOMapper;
import br.com.fiap.catalog.infrastructure.controllers.mappers.ResponseEntityMapper;
import br.com.fiap.catalog.main.Enum.CategoryEnum;
import br.com.fiap.catalog.main.utils.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProductController {
    private final ProductInteractor interactor;
    private final ProductDTOMapper mapper;
    private final ResponseEntityMapper responseEntity;

    public ProductController(ProductInteractor interactor, ProductDTOMapper mapper,
                             ResponseEntityMapper responseEntity) {
        this.interactor = interactor;
        this.mapper = mapper;
        this.responseEntity = responseEntity;
    }

    @Operation(
            summary = "Cadastra um novo produto.",
            description = "Cadastra um novo produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    ResponseEntity<?> create(@RequestParam Integer category,@Valid @RequestBody CreateProductRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) return responseEntity.toEntityBadRequest(Util.bindingResultErrors(result));
            Product productBusiness = mapper.toProduct(request, CategoryEnum.getBySequence(category));
            Product product = interactor.createProduct(productBusiness);
            return responseEntity.toEntityCreated(mapper.toResponse(product)); 
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Edita um produto.",
            description = "Edita um produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PutMapping
    ResponseEntity<?> update(@RequestParam Integer category, @Valid @RequestBody UpdateProductRequest request, BindingResult result){
        try {
            if (result.hasErrors()) return responseEntity.toEntityBadRequest(Util.bindingResultErrors(result));
            Product productBusiness = mapper.toUpdaProduct(request, CategoryEnum.getBySequence(category));
            Product product = interactor.updateProduct(productBusiness);
            return responseEntity.toEntityOk(mapper.toResponse(product)); 
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Busca um produto.",
            description = "Busca um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable String cpf){
        try {
            Product product = interactor.findProductById(Long.valueOf(cpf));
            return responseEntity.toEntityOk(mapper.toResponse(product));
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Listar produtos por categoria.",
            description = "Listar produtos por categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    ResponseEntity<?> findByCategory(@RequestParam Integer category){
        try {
            List<Product> products = interactor.findProductsByCategory(CategoryEnum.getBySequence(category));
            return responseEntity.toEntityOk(mapper.toProductsResponse(products));
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Listar produtos.",
            description = "Listar produtos ordenado por categoria e id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/all")
    ResponseEntity<?> findAll(){
        try {
            List<Product> products = interactor.findAll();
            return responseEntity.toEntityOk(mapper.mapProductsByCategory(products));
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Listar categorias do produto.",
            description = "Listar as categorias pelo id e descrição.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/category")
    ResponseEntity<?> listCategory(){
        try {
            return responseEntity.toEntityOk(
                mapper.categoryToResponse()
            );
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }

    @Operation(
            summary = "Excluir um produto.",
            description = "Excluir um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable String id){
        try {
            interactor.deleteProduct(Long.valueOf(id));
            return responseEntity.toEntityOk(null);
        } catch (CatalogException e) {
            return responseEntity.toEntityBadRequest(Util.errorBody(List.of(e.getMessage())));
        } catch (Exception e) {
            return responseEntity.toEntityInternalServerError(Util.errorBody(List.of("Internal Server Error.", e.getMessage())));
        }
    }
}
