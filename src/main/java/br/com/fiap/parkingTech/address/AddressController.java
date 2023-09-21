package br.com.fiap.parkingTech.address;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    ResponseEntity<AddressView> save(@Valid @RequestBody AddressForm addressForm) {
        AddressView addressView = addressService.save(addressForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addressView.id()).toUri();

        return ResponseEntity.created(uri).body(addressView);
    }

    @GetMapping
    ResponseEntity<List<AddressView>> findAll() {
        List<AddressView> addressesView = addressService.findAll();

        return ResponseEntity.ok(addressesView);
    }

    @GetMapping("/{id}")
    ResponseEntity<AddressView> findById(@PathVariable Long id) {
        AddressView addressesView = addressService.findById(id);

        return ResponseEntity.ok(addressesView);
    }

    @PutMapping("/{id}")
    ResponseEntity<AddressView> update(@PathVariable Long id, @Valid @RequestBody AddressForm addressForm) {
        AddressView addressesView = addressService.update(id, addressForm);

        return ResponseEntity.ok(addressesView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

