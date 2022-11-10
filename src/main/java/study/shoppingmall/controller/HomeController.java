package study.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shoppingmall.dto.ItemSearchDto;
import study.shoppingmall.dto.MainItemDto;
import study.shoppingmall.service.ItemService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemService itemService;

    @GetMapping(value = "/")
    public String Home() {
        return "home";
    }

    @GetMapping(value = "/")
    public String main(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.orElse(0), 8);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}
