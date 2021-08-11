/* eslint-disable jsx-a11y/anchor-is-valid */
/*
    @author Long
 */
import React from 'react';
import '../general-custom/css/NavbarCustom.css';
import logo from "../vendor/font/Lato/NGNL.png"


 const DriverMenu = function() {
    return (
        <div>
            <header className="header" id="header">
            <div className="header_toggle"> <i className='bx bx-menu' id="header-toggle"></i> </div>
            <div className="header_img"> <img src={logo} alt="" /> </div>
            </header>
            <div className="l-navbar" id="nav-bar">
                <nav className="nav">
                    <div> <a href="/driver/container" className="nav_logo"> <i className='bx bx-layer nav_logo-icon'></i> <span className="nav_logo-name">Logistic System</span> </a>
                        <div className="nav_list"> 
                        <a href="/driver/container" className="nav_link"> <i className='bx bx-grid-alt nav_icon'></i> <span className="nav_name">Container</span> </a> 
                        <a href="/driver/history" className="nav_link"> <i className='bx bx-folder nav_icon'></i> <span className="nav_name">History</span> </a> 
                        </div>
                        <a href="/driver/detail" class="nav_link"> <i class='bx bx-user'></i> <span class="nav_name">Profile</span> </a>
                    </div> <a href="http://localhost:8080/log_out" className="nav_link"> <i className='bx bx-log-out nav_icon'></i> <span className="nav_name">Sign Out</span> </a>
                </nav>
            </div>
        </div>
    );
}

export default DriverMenu