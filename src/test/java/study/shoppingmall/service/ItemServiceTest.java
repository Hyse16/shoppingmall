package study.shoppingmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shoppingmall.domain.Item;
import study.shoppingmall.domain.ItemImg;
import study.shoppingmall.domain.ItemStatus;
import study.shoppingmall.dto.ItemFormDto;
import study.shoppingmall.repository.ItemImgRepository;
import study.shoppingmall.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String path = "/Users/hyeonseung/Desktop/Project/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public  void  saveItem() throws Exception {
        ItemFormDto itemFormDto = ItemFormDto.builder()
                .itemNm("테스트 상품입니다")
                .itemStatus(ItemStatus.SELL)
                .itemDetail("테스트 상품 설명입니")
                .price(10000)
                .stockNumber(100)
                .build();
        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        assertEquals(itemFormDto.getItemNm(), item.getName());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImaName());
    }


}