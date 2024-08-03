package org.atlas.framework.excel.easyexcel.product;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

public class ProductStatusConverter implements Converter<ProductStatus> {

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public ProductStatus convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String statusName = context.getReadCellData().getStringValue();
        return ProductStatus.valueOf(statusName);
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<ProductStatus> context) throws Exception {
        ProductStatus status = context.getValue();
        return new WriteCellData<>(status.name());
    }
}
