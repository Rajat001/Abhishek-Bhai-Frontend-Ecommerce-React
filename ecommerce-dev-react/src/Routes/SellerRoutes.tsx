import React from "react";
import { Route, Routes } from "react-router-dom";
import Dashboard from "../seller/pages/SellerDashboard/Dashboard";
import Products from "../seller/pages/Products/Products";
import Payment from "../seller/pages/Payment/Payment";
import Transaction from "../seller/pages/Payment/Transaction";
import Profile from "../seller/pages/Account/Profile";
import Orders from "../seller/pages/Orders/Orders";
import AddProduct from "../seller/pages/Products/AddProduct";
import AddProductForm from "../seller/pages/Products/AddProduct";

const SellerRoutes = () => {
        return (
            <div>

                <Routes>
                    <Route path='/' element={<Dashboard />} />
                    <Route path='/products' element={<Products />} />
                    <Route path='/add-product' element={<AddProductForm />} />
                    <Route path='/orders' element={<Orders />} />
                    <Route path='/account' element={<Profile />} />
                    <Route path='/payment' element={<Payment />} />
                    <Route path='/transaction' element={<Transaction />} />
                    <Route path='/' element={<Dashboard/>} />
                </Routes>

            </div>
        )
}

export default SellerRoutes