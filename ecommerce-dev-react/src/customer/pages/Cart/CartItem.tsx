import { Add, Close, Remove } from "@mui/icons-material";
import { Button, Divider, IconButton } from "@mui/material";
import React from "react";

const CartItem = () => {

    const handleUpdateQuantity=() =>{
        // update cart item quantity
    }

    return (
        <div className="border rounded-md relative">
            <div className="p-5 flex gap-3">

            <div>
                <img className="w-[90px] rounded-md" src="https://rukminim2.flixcart.com/image/1720/1720/xif0q/sari/p/x/r/free-1007-gajgajra-unstitched-original-imahnukndzzmg8pr.jpeg?q=90" alt="" />
            </div>

            <div className="space-y-2">
                    <h1> Virani Clothing</h1>
                    <p> Turquoise Blue Stoework Sating Designer Sarre
                    </p>

                    <p><strong>   Sold by : </strong> Natural LifeSTyle Product Private Limited 
                    </p>
                    <p className="text-sm">7 days replacement available</p>
                    <p className="text-sm text-gray-500"> <strong>quantity : </strong>5 </p>
            </div>
        </div>    

        <Divider/>

        <div className="flex justify-between items-center">
                       <div className="px-5 py-2 flex justify-between items-center">
                <div className="flex items-center gap-2 w-[140px] justify-between">
                           <Button onClick={handleUpdateQuantity} disabled={true} >
                                <Remove />
                            </Button>
                                <span>
                                    {5}
                                </span>
                            <Button onClick={handleUpdateQuantity}>
                                <Add />
                            </Button>
                </div>
            </div>

            <div className="pr-5">
                <p className="text-gray-700 font-medium">₹799</p>
            </div>
        </div>
        
        <div className="absolute top-1 right-1">
            <IconButton color="primary">
                <Close/>
            </IconButton>
        </div>

   </div>
    )
}

export default CartItem