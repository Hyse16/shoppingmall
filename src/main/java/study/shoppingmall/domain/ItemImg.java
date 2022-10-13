package study.shoppingmall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class ItemImg {

    @Id
    @GeneratedValue
    @Column(name = "item_img_id")
    private Long id;

    private String imageName;

    private String oriImaName;

    private String imgUrl;

    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemImg(String imageName, String oriImaName, String imgUrl, String repimgYn, Item item) {
        this.imageName = imageName;
        this.oriImaName = oriImaName;
        this.imgUrl = imgUrl;
        this.repimgYn = repimgYn;
        this.item = item;
    }

    public void updateImg(String oriImaName, String imageName, String imgUrl) {
        this.imageName = imageName;
        this.oriImaName = oriImaName;
        this.imgUrl = imgUrl;

    }
}
