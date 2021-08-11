import React from 'react';
import '../general-custom/css/NavbarCustom.css';
import logo from "../vendor/font/Lato/NGNL.png"

/*
author: The Phap
*/
 const DividerMenu = function() {
    return (
        <div>
            <header class="header" id="header">
            <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
            <div class="header_img"> <img src={logo} alt="aaaâ" /> </div>
            </header>
            <div class="l-navbar" id="nav-bar">
                <nav class="nav">
                    <div> <a href="/divider/driver_list" class="nav_logo"> <i class='bx bx-layer nav_logo-icon'></i> <span class="nav_logo-name">Logistic System</span> </a>
                        <div class="nav_list"> 
                        <a href="/divider/driver_list" class="nav_link"> <i class='bx bx-grid-alt nav_icon'></i> <span class="nav_name">Driver List</span> </a> 
                        <a href="/divider/route" class="nav_link"> <i class='bx bx-archive-in nav_icon' ></i> <span class="nav_name">Route List</span> </a> 
                        <a href="/divider/detail" class="nav_link"> <i class='bx bx-user'></i> <span class="nav_name">Profile</span> </a> 
                        </div>
                    </div> <a href="http://localhost:8080/log_out" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">Sign Out</span> </a>
                </nav>
            </div>
        </div>
    );
}

export default DividerMenu