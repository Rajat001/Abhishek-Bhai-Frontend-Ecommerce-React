import { ElectricBolt } from "@mui/icons-material";
import { Avatar } from "@mui/material";
import { teal } from "@mui/material/colors";
import React from "react";

const OrderItem = () => {
    return (
        <div className="text-sm bg-white p-5 space-y-4 border rounded-md cursor-pointer">
            <div className="flex items-center gap-5">
                <div>
                    <Avatar sizes='small' sx={{bgcolor:teal[500]}}> 
                        <ElectricBolt/>
                    </Avatar>
                </div>

                <div>
                    <h1 className="font-bold text-primary-color">PENDING</h1>
                    <p>Arriving By Mon, 15 Jul</p>
                </div>
            </div>
            <div className="p-5 bg-teal-50 flex gap-3">
                <div>
                    <img className="w-[70px]" src="https://rukminim2.flixcart.com/image/1720/1720/xif0q/watch/y/z/q/2-combo01-jb-empire-men-women-original-imahq4gd5x8ewtty.jpeg?q=90" alt="" />
                </div>
                <div>
                    <h1 className="font-bold">Virani Clothing and watch</h1>
                    <p>JB EMPIRE Arabic Dial Black & White Aura Watch for Men & Women | COMBO01 Analog Watch  - For Men & Women COMBO01 </p>
                    <p>
                        <strong>size : </strong>
                        FREE
                    </p>
                </div>
            </div>
        </div>
    )
}

export default OrderItem