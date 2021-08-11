/* @author: Canh */


import React from 'react';
import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent'
import TodayList from '../hub/HubToDayListComponent'
import { useEffect } from "react";
import Cookies from'universal-cookie'
const cookie = new Cookies();
axios.defaults.withCredentials = true
const HUB_REST_API_URL = 'http://localhost:8080/hub/import';

const HubImportListComponent = function() {

    useEffect( () => {
        if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
        }
     );

        return (
            <div>
                <HubMenu act="import" />
                <TodayList hubview="Import" urlPath={HUB_REST_API_URL} />
            </div>
        );

};

export default HubImportListComponent