

/*
    @author Long
 */
import React from 'react';
import axios from 'axios';
import DriverMenu from './DriverMenuComponent'
import Cookies from "universal-cookie";
const cookie = new Cookies();
axios.defaults.withCredentials = true

const DRIVER_REST_API_URL = 'http://localhost:8080/driver';
class DriverHomeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            allroute: []
        }
    }
   
    componentDidMount() {
        if (typeof cookie.get('driver_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
        axios.get(DRIVER_REST_API_URL).then(response => {
                this.setState({allroute: response.data});
                console.log(response.data);
            });
    }

    render (){
        return (    
            <div>
                <DriverMenu />
                <div class="height-100 bg-light">
                    <h4>Main Components</h4>
                 {}
                </div>
            </div>
        )
    }
}

export default DriverHomeComponent