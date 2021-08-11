/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";
import "../general-custom/css/NavbarCustom.css";
import logo from "../vendor/font/Lato/NGNL.png"

const ShipperMenu = function () {
  return (
    <div>
        <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
        <div class="header_img"> <img src={logo} alt="" /> </div>
        </header>
        <div class="l-navbar" id="nav-bar">
            <nav class="nav">
                <div> <a href="/shipper/package" class="nav_logo"> <i class='bx bx-layer nav_logo-icon'></i> <span class="nav_logo-name">Logistic System</span> </a>
                    <div class="nav_list"> 
                    <a href="/shipper/package" class="nav_link"> <i class='bx bx-grid-alt nav_icon'></i> <span class="nav_name">Package</span> </a> 
                    
                    <a href="/shipper/history" class="nav_link"> <i class='bx bx-folder nav_icon'></i> <span class="nav_name">History</span> </a> 
                    </div>
                    <a href="/shipper/detail" class="nav_link"> <i class='bx bx-user'></i> <span class="nav_name">Profile</span> </a>
                </div> <a href="http://localhost:8080/log_out" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">Sign Out</span> </a>
            </nav>
        </div>
    </div>
);
};

export default ShipperMenu;
