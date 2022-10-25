package study.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shoppingmall.domain.Item;
import study.shoppingmall.domain.ItemImg;
import study.shoppingmall.dto.ItemFormDto;
import study.shoppingmall.repository.ItemImgRepository;
import study.shoppingmall.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        Item item = itemFormDto.toEntity(itemFormDto);
        itemRepository.save(item);

        for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .repimgYn(i == 0 ? "Y" : "N")
                    .build();
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }
}
