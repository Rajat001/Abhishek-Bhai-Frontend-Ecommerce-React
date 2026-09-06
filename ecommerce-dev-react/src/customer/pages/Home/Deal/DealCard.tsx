import React from "react";

const DealCard = () => {
    return (
        <div className="w-[13rem] cursor-pointer">
            <img className="border-x-[7px] border-t-[7px] border-pink-600 w-full h-[12rem] object-cover object-top" 
                src="https://rukminim2.flixcart.com/image/1711/1711/xif0q/smartwatch/5/3/e/35-052-38154pp01-android-ios-fastrack-yes-original-imah4gmnzdj4yy9v.jpeg?q=90" alt="" />
            <div className="border-4 border-black bg-black text-white p-2 text-center">
                <p className="text-lg font-semibold"> Smart Watch</p>
                <p className="text-2xl font-bold"> 20% OFF</p>
                <p className="text-balance text-lg"> shop now </p>
            </div>
        </div>
    );
};

export default DealCard;
