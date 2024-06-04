package net.scottcommerce.stock.service;

import jakarta.transaction.Transactional;
import net.scottcommerce.stock.domain.Stock;
import net.scottcommerce.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import static jakarta.transaction.Transactional.TxType.REQUIRES_NEW;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //TODO: propagation/ no Transactional / synchronized
    @Transactional(REQUIRES_NEW)
    public  void decrease(Long id, Long quantity){
        //Stock 조회
        //재고 감소뒤
        //갱신된 값을 저장하도록
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);

        //TODO: Transactional
        //TODO: Executor


    }
}
