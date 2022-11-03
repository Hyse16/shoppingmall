package study.shoppingmall.dto;

import lombok.Data;
import study.shoppingmall.domain.ItemStatus;

@Data
public class ItemSearchDto {

    private String searchDateType;
    private ItemStatus searchSellStatus;
    private String searchBy;
    private String searchQuery = "";


}