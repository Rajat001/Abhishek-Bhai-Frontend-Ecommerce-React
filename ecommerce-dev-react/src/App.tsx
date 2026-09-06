import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart'; // for adding Icons...
import Navbar from './customer/components/Navbar/Navbar';
import customeTheme from './Theme/customeTheme';
import Home from './customer/pages/Home/Home'; // this needed to be added for adding below component i.e. <Home /> ...
import Product from './customer/pages/Product/Product';


function App() {
  return (
            <ThemeProvider theme={customeTheme}>
                <div>
                     <Navbar />
                     {/* <Home /> */}

                     <Product/>
                </div>
                     
            </ThemeProvider>
  );
}

export default App;
