package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.exception.ItemNotFoundException;
import br.com.fiap.chapecos.mapper.ItemMapper;
import br.com.fiap.chapecos.model.Item;
import br.com.fiap.chapecos.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    public final ItemRepository itemRepository;

    public final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemResponseDTO create(ItemRequestDTO dto) {
        Item item = itemMapper.toModel(dto);

        return new ItemResponseDTO(itemRepository.save(item));
    }

    public List<ItemResponseDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    public ItemResponseDTO findById(Long idItem) {
        Item item = itemRepository.findById(idItem)
                .orElseThrow(ItemNotFoundException::new);

        return new ItemResponseDTO(item);
    }

    public void delete(Long idItem) {
        Item item = itemRepository.findById(idItem)
                .orElseThrow(ItemNotFoundException::new);

        itemRepository.delete(item);
    }
}
