package ubb.postuniv.riddingaddict.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.AppUserDTOResponse;
import ubb.postuniv.riddingaddict.model.dto.ProductDTOResponse;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import java.util.List;

@Component
public class AppUserResponseMapper extends AbstractMapper<AppUser, AppUserDTOResponse> {

    @Autowired
    private Mapper<Product, ProductDTOResponse> productMapper;

    @Override
    public AppUser convertDtoToModel(AppUserDTOResponse appUserDTOResponse) {

        List<Product> products = productMapper.convertDtosToModels(appUserDTOResponse.getProducts());

        return new AppUser(appUserDTOResponse.getUserCode(),
                appUserDTOResponse.getFirstName(),
                appUserDTOResponse.getLastName(),
                appUserDTOResponse.getUsername(),
                appUserDTOResponse.getEmail(),
                products,
                appUserDTOResponse.getRoles());
    }

    @Override
    public AppUserDTOResponse convertModelToDto(AppUser appUser) {

        List<ProductDTOResponse> products = productMapper.convertModelsToDtos(appUser.getProducts());

        return new AppUserDTOResponse(appUser.getUserCode(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRoles(),
                products);
    }
}
