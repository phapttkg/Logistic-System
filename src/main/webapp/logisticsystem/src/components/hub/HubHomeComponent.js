/* @author: Canh */


import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent'
import TodayList from '../hub/HubToDayListComponent'
import Cookies from'universal-cookie'
import { useEffect } from "react";

axios.defaults.withCredentials = true;

const cookie = new Cookies();


const HUB_REST_API_URL = 'http://localhost:8080/hub/';

const HubHomeComponent = function(){
    
    useEffect( () => {
         if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    }
    );
        return (    
            <div>
                <HubMenu />
                <TodayList hubview="Total" urlPath={HUB_REST_API_URL} />
            </div>
        )
    }


export default HubHomeComponent