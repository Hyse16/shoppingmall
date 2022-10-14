package study.shoppingmall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shoppingmall.domain.Item;
import study.shoppingmall.domain.ItemStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemFormDto {

    private Long id;


    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상세 내용은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message ="재고는 필수 입력 값입니다." )
    private Integer stockNumber;

    private ItemStatus itemStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    @Builder
    public ItemFormDto(String itemNm, Integer price, String itemDetail, Integer stockNumber, ItemStatus itemStatus) {
        this.itemNm = itemNm;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemStatus = itemStatus;
    }

    public Item toEntity(ItemFormDto dto) {

        return Item.builder()
                .name(dto.itemNm)
                .itemDetail(dto.itemDetail)
                .itemStatus(dto.itemStatus)
                .price(dto.price)
                .stockQuantity(dto.stockNumber)
                .build();
    }

    public ItemFormDto of(Item entity) {

        return ItemFormDto.builder()
                .itemNm(entity.getName())
                .itemDetail(entity.getItemDetail())
                .itemStatus(entity.getItemStatus())
                .price(entity.getPrice())
                .stockNumber(entity.getStockQuantity())
                .build();
    }

}
