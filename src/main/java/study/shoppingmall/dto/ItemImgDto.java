package study.shoppingmall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import study.shoppingmall.domain.ItemImg;

@Data
@RequiredArgsConstructor
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @Builder
    public ItemImgDto(String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity(ItemImgDto dto) {


        return ItemImg.builder()
                .imageName(dto.imgName)
                .oriImaName(dto.oriImgName)
                .imgUrl(dto.imgUrl)
                .repimgYn(dto.repImgYn)
                .build();


    }
    public ItemImgDto of(ItemImg entity) {

        return ItemImgDto.builder()
                .imgName(entity.getImageName())
                .oriImgName(entity.getOriImaName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepimgYn())
                .build();
    }
}
