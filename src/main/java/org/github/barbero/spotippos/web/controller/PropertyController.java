package org.github.barbero.spotippos.web.controller;

import org.github.barbero.spotippos.dto.web.RequestPropertyDTO;
import org.github.barbero.spotippos.dto.web.ResponsePropertyDTO;
import org.github.barbero.spotippos.dto.web.PropertySearchDTO;
import org.github.barbero.spotippos.services.PropertyService;
import org.github.barbero.spotippos.web.exception.InvalidRequestException;
import org.github.barbero.spotippos.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller to handle requests to Properties resource.
 *
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(value = "/properties", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PropertyController {

    private PropertyService propertyService;

    @Autowired
    public PropertyController(final PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Return a ${@link org.github.barbero.spotippos.model.entity.Property} for the given id.
     *
     * @param id The ${@link org.github.barbero.spotippos.model.entity.Property}'s id
     * @return Status Code 200 with ${@link org.github.barbero.spotippos.model.entity.Property} at response body in case of success
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponsePropertyDTO> get(@PathVariable Long id) {
        final Optional<ResponsePropertyDTO> propertyDTO = this.propertyService.get(id);
        propertyDTO.orElseThrow(() -> new ResourceNotFoundException(id));
        return ResponseEntity.ok(propertyDTO.get());
    }

    /**
     * Return a ${@link PropertySearchDTO} for the given coordinates.
     *
     * @param ax Upper Left x position
     * @param ay Upper Left y position
     * @param bx Bottom Right x position
     * @param by Bottom Right y position
     * @return Status code 200 with ${@link PropertySearchDTO} at response body in case of success
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PropertySearchDTO> get(@RequestParam Integer ax, @RequestParam Integer ay,
                                                 @RequestParam Integer bx, @RequestParam Integer by) {
        final Optional<PropertySearchDTO> propertySearchDTO = this.propertyService.get(ax, ay, bx, by);
        propertySearchDTO.orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(propertySearchDTO.get());
    }

    /**
     * Create a new ${@link org.github.barbero.spotippos.model.entity.Property}.
     *
     * @param property   The ${@link ResponsePropertyDTO} to be persisted
     * @param result     The ${@link BindingResult} from request
     * @param uriBuilder The ${@link UriComponentsBuilder} to build the Location header
     * @return StatusCode 201 with Location Header in case of success
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> post(@RequestBody @Valid RequestPropertyDTO property, BindingResult result,
                                     UriComponentsBuilder uriBuilder) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        final ResponsePropertyDTO savedProperty = this.propertyService.save(property);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/properties/{id}").buildAndExpand(savedProperty.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
