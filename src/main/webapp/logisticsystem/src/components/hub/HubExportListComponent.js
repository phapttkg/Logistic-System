/* @author: Canh */


import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent'
import TodayList from '../hub/HubToDayListComponent'
import { useEffect } from "react";
import Cookies from'universal-cookie'
const cookie = new Cookies();
axios.defaults.withCredentials = true
const HUB_REST_API_URL = 'http://localhost:8080/hub/export';
const HubExportListComponent = function(){
    useEffect( () => {
        if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
        }
     );
        return (
            <div>
                <HubMenu act="export" />
                <TodayList hubview="Export" urlPath={HUB_REST_API_URL} />
            </div>
        )
    }

export default HubExportListComponent
