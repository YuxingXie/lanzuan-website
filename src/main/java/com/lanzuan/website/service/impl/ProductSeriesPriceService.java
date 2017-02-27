package com.lanzuan.website.service.impl;

import com.lanzuan.common.base.BaseEntityManager;
import com.lanzuan.common.base.EntityDao;
import com.lanzuan.entity.ProductSeries;
import com.lanzuan.entity.ProductSeriesPrice;
import com.lanzuan.entity.ProductSubCategory;
import com.lanzuan.website.dao.ProductSeriesPriceDao;
import com.lanzuan.website.service.IProductSeriesPriceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductSeriesPriceService  extends BaseEntityManager<ProductSeriesPrice> implements IProductSeriesPriceService {
    @Resource
    private ProductSeriesPriceDao productSeriesPriceDao;
    protected EntityDao<ProductSeriesPrice> getEntityDao() {
        return this.productSeriesPriceDao;
    }

    @Override
    public List<ProductSeriesPrice> findByProductSeriesId(String id) {
        return productSeriesPriceDao.findByProductSeriesId(id);
    }

    @Override
    public ProductSeriesPrice findCurrentPriceByProductSeriesId(String productSeriesId) {
        List<ProductSeriesPrice> prices=findByProductSeriesId(productSeriesId);
        Date now=new Date();
        for (ProductSeriesPrice price:prices){
            Assert.notNull(price.getBeginDate());
            if (price.getEndDate()==null){
                if (now.after(price.getBeginDate())){
                    return price;
                }
            }else {
                if (now.after(price.getBeginDate())&&now.before(price.getEndDate())){
                    return price;
                }
            }
        }
        return null;
    }

    @Override
    public Page<ProductSeries> getProductSeriesOrderByPriceInProductSubCategory(ProductSubCategory productSubCategory, Integer currentPage, Integer pageSize, boolean asc) {
        return productSeriesPriceDao.getProductSeriesOrderByPriceInProductSubCategory(productSubCategory,currentPage,pageSize,asc);
    }
}
