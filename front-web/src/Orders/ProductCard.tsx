import { formatPrice } from './helpers';
import { Product } from './types';
//Recebe toda a estrutura dos cartões com descrição de produtos
type Props = {
  product: Product;
  onSelectProduct: (product: Product) => void;
  isSelected: boolean; //verifica se o produto foi selecionado
}


function ProductsCard({product, onSelectProduct, isSelected}:Props) {
    return (
    <div className={`order-card-container ${isSelected ? 'selected' : ''}`}
           onClick={() => onSelectProduct(product)}
      >
          <h3 className="order-card-title">
            {product.name}
          </h3>
          <img src={product.imageUri} 
          className="order-card-image" 
          alt={product.name}/>
          <h3 className="order-card-price">
           {formatPrice(product.price)}
          </h3>
          <div className="order-card-description">
              <h3>Descrição</h3>
              <p>
              {product.description} 
              </p>
          </div>
      </div>
    );
  }
  
  export default ProductsCard;