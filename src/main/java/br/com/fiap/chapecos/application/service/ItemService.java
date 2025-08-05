package br.com.fiap.chapecos.application.service;

import br.com.fiap.chapecos.adapter.inbound.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.infrastructure.exception.ItemNotFoundException;
import br.com.fiap.chapecos.adapter.outbound.mapper.ItemMapper;
import br.com.fiap.chapecos.domain.model.Item;
import br.com.fiap.chapecos.domain.repository.ItemRepository;
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
