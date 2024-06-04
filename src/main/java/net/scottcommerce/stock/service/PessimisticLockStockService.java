package net.scottcommerce.stock.service;

import jakarta.transaction.Transactional;
import net.scottcommerce.stock.domain.Stock;
import net.scottcommerce.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);

        stock.decrease(quantity);

        stockRepository.save(stock);
    }
}
