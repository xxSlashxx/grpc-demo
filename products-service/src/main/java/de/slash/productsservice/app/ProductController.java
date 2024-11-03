package de.slash.productsservice.app;

import com.google.protobuf.Empty;
import de.slash.productsservice.product.Product;
import de.slash.productsservice.product.ProductService;
import de.slash.productsservice.system.StreamObserverUtility;
import de.slash.stubs.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@GrpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public StreamObserver<ProductRequest> createProduct(StreamObserver<ProductIdResponse> responseObserver) {
        return StreamObserverUtility.proxyStream(responseObserver, this::createProduct);
    }

    private ProductIdResponse createProduct(ProductRequest request) {
        BigDecimal price = new BigDecimal(request.getPrice());
        Product createdProduct = productService.createProduct(request.getName(), price);
        return ProductIdResponse.newBuilder().setId(createdProduct.getId()).build();
    }

    @Override
    public StreamObserver<ProductIdRequest> getProduct(StreamObserver<ProductResponse> responseObserver) {
        return StreamObserverUtility.proxyStream(responseObserver, this::getProduct);
    }

    private ProductResponse getProduct(ProductIdRequest request) {
        Product product = productService.getById(request.getId());
        return ProductResponse.newBuilder().setId(product.getId()).setName(product.getName()).setPrice(product.getPrice().toPlainString()).build();
    }

    @Override
    public StreamObserver<ProductIdRequest> deleteProduct(StreamObserver<Empty> responseObserver) {
        return StreamObserverUtility.proxyStream(responseObserver, this::deleteProduct);
    }

    private Empty deleteProduct(ProductIdRequest request) {
        Product product = productService.getById(request.getId());
        productService.deleteProduct(product);
        return Empty.newBuilder().build();
    }
}
