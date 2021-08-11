import React from 'react';
import '../general-custom/css/NavbarCustom.css';
import logo from "../vendor/font/Lato/NGNL.png"

// @author: Canh
 const HubMenu = function(props) {
    return (
        <div>
            <header className="header" id="header">
            <div className="header_toggle"> <i className='bx bx-menu' id="header-toggle"></i> </div>
            <div className="header_img"> <img src={logo} alt="99999" /> </div>
            </header>
            <div className="l-navbar" id="nav-bar">
                <nav className="nav">
                    <div> <a href="/hub" className="nav_logo"> <i className='bx bx-layer nav_logo-icon'></i> <span className="nav_logo-name">Logistic System</span></a>
                        <div className="nav_list">
                        {(props.act === 'package')
                        ?
                        <a href="/hub/package" className="nav_link active"> <i className='bx bx-grid-alt nav_icon'></i> <span className="nav_name">Package</span></a>
                        :
                        <a href="/hub/package" className="nav_link"> <i className='bx bx-grid-alt nav_icon'></i> <span className="nav_name">Package</span></a> }
                        {(props.act === 'import')
                        ?
                        <a href="/hub/import" className="nav_link active"> <i className='bx bx-archive-in nav_icon' ></i> <span className="nav_name">Import</span></a>
                        :
                        <a href="/hub/import" className="nav_link"> <i className='bx bx-archive-in nav_icon' ></i> <span className="nav_name">Import</span></a>}
                        {(props.act === 'export')
                        ?
                        <a href="/hub/export" className="nav_link active"> <i className='bx bx-archive-out nav_icon' ></i> <span className="nav_name">Export</span></a>
                        :
                        <a href="/hub/export" className="nav_link"> <i className='bx bx-archive-out nav_icon' ></i> <span className="nav_name">Export</span></a>}
                        {(props.act === 'shipper')
                        ?
                        <a href="/hub/shipper_list" className="nav_link active"> <i className='bx bx-user nav_icon'></i> <span className="nav_name">Shippers</span></a>
                        :
                        <a href="/hub/shipper_list" className="nav_link"> <i className='bx bx-user nav_icon'></i> <span className="nav_name">Shippers</span></a>}

                        </div>
                        <a href="/hub/detail" class="nav_link"> <i class='bx bx-user'></i> <span class="nav_name">Profile</span> </a>
                    </div> <a href="http://localhost:8080/log_out" className="nav_link"> <i className='bx bx-log-out nav_icon'></i> <span className="nav_name">Sign Out</span> </a>
                </nav>
            </div>
        </div>
    );
}

export default HubMenu