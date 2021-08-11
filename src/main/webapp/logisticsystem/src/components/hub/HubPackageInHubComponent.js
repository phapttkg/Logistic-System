/* @author: Canh */


import React, { useEffect, useState } from 'react';
import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent';
import './css/tableCustom.css';
import Cookies from'universal-cookie'
const cookie = new Cookies();

axios.defaults.withCredentials = true
const HUB_REST_API_URL = 'http://localhost:8080/hub/package';

const PackageDetail = function(props) {
    return(
        <div className="card bg-light mb-3 package-information" >
            <div className="card-header" style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>{props.pkg.pkg_id}</div>
            <div className="card-body">
                <h5 className="card-title">{props.pkg.pkg_id}</h5>
                <p className="card-text" >
                    <span>Created Time: {props.pkg.created_datetime} </span> <br></br>
                    <span>Delivery Fee: {props.pkg.delivery_fee}</span> <br></br>
                    <span>COD Value: {props.pkg.cod_value}</span> <br></br>
                    <span>Receiver Name: {props.pkg.receiver_name}</span> <br></br>
                    <span>Receiver Phone Number: {props.pkg.receiver_name}</span> <br></br>
                    <span>Receiver Address: {props.pkg.receiver_phone_num}</span> <br></br>
                    <span>Sender Name: {props.pkg.sender_name}</span> <br></br>
                    <span>Sender Phone Number: {props.pkg.sender_phone_num}</span><br></br>
                    <span>Tracking Status: {props.pkg.tracking_status}</span>
                </p>
            </div>
        </div>
    );
};

const HubPackageInHubComponent = function(props) {
    const [packageList, setPackageList] = useState([]);
    const [pkg, setPkg] = useState({});
    const [checked, setChecked] = useState(true);
    

    useEffect(() => {
        if (typeof cookie.get('hub_staff_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
        axios.get(HUB_REST_API_URL).then(response => {
            setPackageList(response.data);
            setPkg(response.data[0]);
        });
    },[]);

    return(
        <div className="container" style={{marginTop:"10vh", fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                <HubMenu act='package' />
                <h1 className="mx-auto text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>Package List In Hub</h1>
                <div className="package-list-show row justify-content-around shadow-lg p-3 mb-5 bg-gray rounded" style={{height: "60vh"}}>
                <div className="package-list col-md-5 mh-100 overflow-auto mb-4">
                    <table className="table table-striped table-fixed">
                        <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                            <tr>
                                <th scope="col">PACKAGE ID</th>
                                <th scope="col">CREATED TIME</th>
                                <th scope="col">DETAIL</th>
                            </tr>
                        </thead>
                        <tbody>
                            {  
                                packageList.map(
                                    pkgDetail =>
                                    (typeof pkgDetail !== 'undefined')
                                    ?
                                    <tr key={"pkg" + pkgDetail.pkg_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                        <th scope="row">{pkgDetail.pkg_id}</th>
                                            <td>{pkgDetail.created_datetime}</td>
                                            <td>
                                                <div className="form-check">
                                                    {
                                                        (pkgDetail.pkg_id === pkg.pkg_id) 
                                                        ? 
                                                        <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}}  name="flexRadioDefault" id={"flexRadioDefault" + pkgDetail.pkg_id} onClick={() => {setPkg(pkgDetail)}} defaultChecked={checked} onChange={() => setChecked(!checked)} />
                                                        : 
                                                        <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault" id={"flexRadioDefault" + pkgDetail.pkg_id} onClick={() => {setPkg(pkgDetail)}}   />
                                                    }   
                                                    
                                                </div>
                                            </td>
                                    </tr>
                                    :""
                                )
                                
                            }
                            
                        </tbody>
                    </table>
                </div>
                    <div className="shipper-detail col-md-6">
                        {(typeof pkg !== 'undefined')
                        ?
                        <PackageDetail pkg={pkg} />
                        :""}
                    </div>
                </div>
            </div>
    );
};

export default HubPackageInHubComponent