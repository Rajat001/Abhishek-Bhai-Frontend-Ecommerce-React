import React from "react";
import "./ShopByCategory.css";

const ShopByCategoryCard = () => {
    return (
        <div className="flex gap-3 flex-col justify-center items-center group cursor-pointer">
                <div className="custome-border w-[150px] h-[150px] lg:w-[249px] lg:h-[249px] rounded-full bg-primary-color">
                        <img className='rounded-full group-hover:scale-95 transition-transform transform-duration-700 object-cover object-top h-full w-full' 
                        src="https://rukminim2.flixcart.com/image/1720/1720/xif0q/dining-table/l/j/c/61-4-seater-1-particle-board-smart-multi-use-foldable-white-original-imagzfkzgt7byhuj.jpeg?q=90" alt="Shop By Category" />
                </div>

                <h1> Kitchen & Table </h1>
        </div>
    );
};

export default ShopByCategoryCard;
