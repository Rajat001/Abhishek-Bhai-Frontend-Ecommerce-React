import React from "react";

const SimilarProductCard = () => {

    return (
        <div>
        <div className='group px-4 relative'>
            <div className='card'>
                   <img className='card-media object-top'
                   src={"https://rukminim2.flixcart.com/image/1720/1720/xif0q/sari/b/e/f/free-banarasi-pan-single-pink-gold-somvati-unstitched-resized-original-imahh6yny3ahtjez.jpeg?q=90"} alt=""
                   /> 
                    
            </div>


                    <div className='details pt-3 space-y-1 group-hover-effect rounded-md'>
                        <div className='name'>
                        <h1>Niky</h1>
                        <p>Blue Shirt</p>
                        </div>

                        <div className='price flex items-center gap-3'>
                            <span className="font-sans text-gray-800">
                                $ 400
                            </span>

                            <span className="thin-line-through text-gray-400">
                                $ 900
                            </span>

                            <span className='text-primary-color font-semibold'>
                                60%
                            </span>
                        </div>
                    </div>

        </div>


        </div>
    )

}

export default SimilarProductCard