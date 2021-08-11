import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "./css/fontCustom.css";
import Cookies from 'universal-cookie';

const cookie = new Cookies();
const lghub_id = cookie.get("lghub_id");
axios.defaults.withCredentials = true;

/* @author: Canh */

const ShipRouteDetail = function(props) {

    return (
        <div className="card bg-light mb-3 shiproute-information" >
            <div className="card-header" style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                SHIP ROUTE ID:
                 {
                    (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0)
                    ?
                    props.shipRouteDetail.ship_route_id
                    :""
                 }

            </div>
            <div className="card-body" style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                <h6 className="card-title" >
                {
                    (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0) 
                    ?
                    ("Shipper ID: "+ props.shipRouteDetail.shipper_id)
                    :""
                }
                </h6>   
                <p className="card-text">
                    {
                        (typeof props.shipRouteDetail !== 'undefined') 
                        ?
                        (props.shipRouteDetail.packagelist).map( pkg => 
                            <span key={"shpr" + pkg.pkg_id} >{pkg.pkg_id}</span>
                        )
                        :""
                    }

          
                </p>
            </div>
        </div>
    );
};

const ImportShipRouteDetail = function(props) {

    return (

            <div className="overflow-auto">
            <table className="table table-striped">
                    <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                        <tr>
                            <th scope="col">SHIP ROUTE ID: 
                                {
                                    (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0)
                                    ?
                                    (props.shipRouteDetail.ship_route_id)
                                    :""
                                }
                            </th>
                            <th scope="col">IMPORT ACTION</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0) 
                            ?
                            (props.shipRouteDetail.packagelist).map(
                                pkg => 
                                (!pkg.tracking_status.includes("deliver") && pkg.current_hub === '-1')
                                ?
                                <tr key={"shpr" + pkg.pkg_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                    <th> Package ID: {pkg.pkg_id}</th>
                                    <td>
                                        <div className="form-check">
                                            <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => importActionPackage(pkg.pkg_id)}>Import</button>
                                        </div>
                                    </td>
                                </tr>
                                :""
                            )
                            :""
                        }
                    </tbody>
            </table>
            </div>
    );
};

const ExportShipRouteDetail = function(props) {

    return (

        <div className="overflow-auto">
        <table className="table table-striped">
                <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                    <tr>
                        <th scope="col">SHIP ROUTE ID: 
                            {
                                (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0)
                                ?
                                (props.shipRouteDetail.ship_route_id)
                                :""
                            }
                        </th>
                        <th scope="col">EXPORT ACTION</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        (typeof props.shipRouteDetail !== 'undefined' && Object.keys(props.shipRouteDetail).length > 0) 
                        ?
                        (props.shipRouteDetail.packagelist).map(
                            pkg => 
                            (pkg.current_hub !== '-1')
                            ?
                            <tr key={"shpr" + pkg.pkg_id}  style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                <th> Package ID: {pkg.pkg_id}</th>
                                <td>
                                    <div className="form-check">
                                        <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => exportActionPackage(pkg.pkg_id)}>Export</button>
                                    </div>
                                </td>
                            </tr>
                            :""
                        )
                        :""
                    }
                </tbody>
        </table>
        </div>
);
};

const RouteDetail = function(props) {

        return (
            (typeof props.routeDetail !== 'undefined' && Object.keys(props.routeDetail).length > 0)
            ?
            (
                <div className="card bg-light route-information" >
                    
                    
                    <div className="card-header" style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                        {(props.routeDetail.start_pos.includes(props.pos) ) ? "ACTION: EXPORT" :  "ACTION: IMPORT"}
                    </div>
                    <div className="card-body"style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                        <h6 className="card-title">ROUTE ID: {props.routeDetail.route_id}</h6>
                        <p className="card-text">
                            <span>Driver: {props.routeDetail.driver_id}</span> <br></br>
                            <span>Start Pos: {props.routeDetail.start_pos}</span> <br></br>
                            <span>End Pos: {props.routeDetail.end_pos}</span> <br></br>
                            <span>Start Time: {props.routeDetail.start_datetime}</span> <br></br>
                            <span>End Time: {props.routeDetail.end_datetime}</span> <br></br>
                        </p>
                    </div>
                    
                </div>
            )
            :""
        );
        
};

const importActionPackage = function(props) {
    axios.put("http://localhost:8080/hub/import/import_action.do", {pcode: props});
    window.location.reload();  
};

const importActionRoute = function(props) {
    axios.put("http://localhost:8080/hub/import/import_action.do", {drcode: props});
    window.location.reload();
};

const importActionShipRoute = function(props) {
    axios.put("http://localhost:8080/hub/import/import_action.do", {srcode: props});
    window.location.reload();
}


const ImportRouteDetail = function(props) {

    return (
        <div className="overflow-auto">
            <table className="table table-striped">
                    <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                        <tr>
                            <th scope="col">
                            {(typeof props.routeDetail !== 'undefined' && Object.keys(props.routeDetail).length > 0)
                            ?
                            (props.routeDetail.route_id)
                            :""}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            (typeof props.routeDetail.packagelist !== 'undefined' && Object.keys(props.routeDetail).length > 0)
                            ?
                            (props.routeDetail.packagelist).map(
                                pkg => 
                                (pkg.current_hub === "-1")
                                ?
                                <tr key={"imr" + pkg.pkg_id}>
                                    <th>Package ID: {pkg.pkg_id}</th>
                                </tr>
                                :""
                            )
                            :""
                        }
                    </tbody>
            </table>
        </div>
    );
};

const exportActionPackage = function(props) {
    axios.put("http://localhost:8080/hub/export/export_action.do", {pcode: props});
    window.location.reload();
};

const exportActionRoute = function(props) {
    axios.put("http://localhost:8080/hub/export/export_action.do", {drcode: props});
    window.location.reload();
};

const exportActionShipRoute = function(props) {
    axios.put("http://localhost:8080/hub/export/export_action.do", {srcode: props});
    window.location.reload();
}


const ExportRouteDetail = function(props) {

    return (
        <div className="overflow-auto">
            <table className="table table-striped">
                    <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                        <tr>
                            <th scope="col">
                                {(typeof props.routeDetail !== 'undefined' && Object.keys(props.routeDetail).length > 0)
                                ?
                                    (props.routeDetail.route_id)
                                :""}
                            </th>
                            {/* <th scope="col">EXPORT ACTION</th> */}
                        </tr>
                    </thead>
                    <tbody>
                        {
                            (typeof props.routeDetail.packagelist !== 'undefined' && Object.keys(props.routeDetail).length > 0)
                            ?
                            (props.routeDetail.packagelist).map(
                                pkg => 
                                (pkg.current_hub !== "-1" && pkg.next_hub !== 'final')
                                ?
                                <tr key={"exr"+pkg.pkg_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                    <th>Package ID: {pkg.pkg_id}</th>
                                    {/* <td>
                                        <div className="form-check">
                                            <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => exportActionPackage(pkg.pkg_id)}>Export</button>
                                        </div>
                                    </td> */}
                                </tr>
                                :""
                            )
                            :""
                        }
                    </tbody>
            </table>
        </div>
    );
};

const TodayList = function(props) {
    const [allRoute, setAllRoute] = useState([]);
    const [route, setRoute] = useState({});
    const [shipRoute, setShipRoute] = useState({});
    const [importShipRoute, setImportShipRoute] = useState({});
    const [exportShipRoute, setExportShipRoute] = useState({});
    const [schecked, setSChecked] = useState(true);
    const [rchecked, setRChecked] = useState(true);

    useEffect(() => {
        axios.get(props.urlPath).then(response => {
            setAllRoute(response.data);

            if (props.urlPath.includes("import") || props.urlPath.includes("export")) {
                setRoute(response.data[0][0]);
                setShipRoute(response.data[1][0]);
                // setImportShipRoute(shipRoute);
                // setExportShipRoute(shipRoute);
            }
            else {
                (response.data[0][0] != null) ? setRoute(response.data[0][0]) : setRoute(response.data[1][0]);
                setShipRoute(response.data[2][0]);
            }
        });
    },[props.urlPath]);

    return (
        <div className="container" style={{marginTop:"10vh"}}>
            {(typeof route !== 'undefined' && Object.keys(route).length > 0)
            ?
                <h1 className="mx-auto text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>{props.hubview} Route List Today</h1>
            :
                <h1 className="mx-auto text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>DRIVER REST DAY</h1>
            }

            {(typeof route !== 'undefined' && Object.keys(route).length > 0)
            ?
                <div className="route-list-show row justify-content-around shadow-lg p-3 mb-5 bg-gray rounded" style={{height: "40vh"}}>
                <div className="route-list col-md-5 mh-100 overflow-auto mb-4">
                    <table className="table table-striped">
                        <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                            <tr>
                                <th scope="col">ROUTE ID</th>
                                <th scope="col">DETAIL</th>
                                {(props.hubview !== 'Total')
                                ?
                                <th scope="col">ACTION</th>
                                :""}
                            </tr>
                        </thead>
                        <tbody >
                            {
                                allRoute.map(
                                    routes => routes.filter(detail => detail.route_id != null).map(
                                        rdetail => 
                                        <tr key={"route" + rdetail.route_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                            <th scope="row">{rdetail.route_id}</th>
                                                <td>
                                                    <div className="form-check">
                                                        {
                                                            (rdetail.route_id === route.route_id) 
                                                            ? 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault" id={"flexRadioDefault" + rdetail.route_id} onClick={() => setRoute(rdetail) } onChange={() => setRChecked(!rchecked)} defaultChecked={rchecked} />
                                                            : 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault" id={"flexRadioDefault" + rdetail.route_id} onClick={() => {setRoute(rdetail)}}   />
                                                        }
                                                    </div>
                                                </td>
                                                {(props.hubview !== 'Total')
                                                ?
                                                <td>
                                                    {(props.hubview === 'Import')
                                                    ?
                                                    <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => importActionRoute(rdetail.route_id)}>{props.hubview}</button>   
                                                    :
                                                    <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => exportActionRoute(rdetail.route_id)}>{props.hubview}</button> }
                                                </td>
                                                :""}
                                        </tr>
                                    )
                                )
                            }
                        </tbody>
                    </table>
                </div>
                    <div className="route-detail col-md-7">
                        {
                            (props.urlPath.includes("import") || props.urlPath.includes("export"))
                            ?
                            (props.urlPath.includes("import")) ? <ImportRouteDetail routeDetail={route} /> : <ExportRouteDetail routeDetail={route}/>
                            :
                            <RouteDetail routeDetail={route} pos={lghub_id}/>
                        }

                    </div>
            </div>
            :""}

            {(typeof shipRoute !== 'undefined' && Object.keys(shipRoute).length > 0)
            ?
            <h1 className="mx-auto text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>{props.hubview} Ship Route List Today</h1>
            :
            <h1 className="mx-auto text-center" style={{fontFamily:"Lato-Bold", lineHeight:"1.4", color:"#808080"}}>SHIPPER REST DAY</h1>
            }


            {(typeof shipRoute !== 'undefined' && Object.keys(shipRoute).length > 0)
            ?
            <div className="route-list-show row justify-content-around shadow-lg p-3 mb-5 bg-gray rounded" style={{height: "40vh"}}>
                <div className="route-list col-md-5 mh-100 overflow-auto mb-4">
                    <table className="table table-striped">
                        <thead style={{background:"#6c7ae0", color:"#fff", fontFamily:"Lato-Bold",lineHeight:"1.4",fontSize:"18px"}}>
                            <tr>
                                <th scope="col">SHIP ROUTE ID</th>
                                <th scope="col">DETAIL</th>
                                {(props.hubview !== 'Total')
                                ?
                                <th scope="col">ACTION</th>
                                :""}
                            </tr>
                        </thead>
                        <tbody>
                        {
                                (props.hubview === 'Total')
                                ?
                                allRoute.map(
                                    routes => routes.filter(detail => detail.ship_route_id != null).map(
                                        detail =>
                                        <tr key={"route" + detail.ship_route_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                            <th scope="row">
                                                {
                                                    (detail.end_time === null)
                                                    ?
                                                    detail.ship_route_id
                                                    :
                                                    detail.ship_route_id+ " ( DONE )"
                                                }
                                            </th>
                                                <td>
                                                    <div className="form-check">
                                                        {
                                                            (detail.ship_route_id === shipRoute.ship_route_id) 
                                                            ? 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault2" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setShipRoute(detail)}} defaultChecked={schecked} onChange={() => setSChecked(!schecked)} />
                                                            : 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault2" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setShipRoute(detail)}}   />
                                                        }
                                                    </div>
                                                </td>
                                        </tr>
                                    )
                                )
                                :
                                (props.hubview === 'Import')
                                ?
                                allRoute.map(
                                    routes => routes.filter(detail => detail.ship_route_id != null && checkShipImported(detail.packagelist)).map(
                                        detail =>
                                        
                                        <tr key={"route" + detail.ship_route_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                            <th scope="row">{detail.ship_route_id}</th>
                                                <td>
                                                    <div className="form-check">
                                                        {
                                                            (detail.ship_route_id === shipRoute.ship_route_id) 
                                                            ? 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault2" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setImportShipRoute(detail)}} onChange={() => { setSChecked(!schecked); setImportShipRoute(detail)}} />
                                                            : 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault2" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setImportShipRoute(detail)}}   />
                                                        }
                                                    </div>
                                                </td>
                                                <td>                                                    
                                                    <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => importActionShipRoute(detail.ship_route_id)}>{props.hubview}</button>                                                    
                                                </td>

                                        </tr>
                                    )
                                )
                                :
                                allRoute.map(
                                    routes => routes.filter(detail => detail.ship_route_id !== null && detail.end_time === null && checkShipExported(detail.packagelist)).map(
                                        detail =>
                                        <tr key={"route" + detail.ship_route_id} style={{fontFamily:"Lato-Regular", fontSize:"15px", lineHeight:"1.4", color:"#808080"}}>
                                            <th scope="row">{detail.ship_route_id}</th>
                                                <td>
                                                    <div className="form-check">
                                                        {
                                                            (detail.ship_route_id === shipRoute.ship_route_id) 
                                                            ? 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault3" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setExportShipRoute(detail)}} defaultChecked={schecked} onChange={() => { setSChecked(!schecked); setExportShipRoute(detail)}}/>
                                                            : 
                                                            <input className="form-check-input" type="radio" style={{backgroundColor:"#6c7ae0"}} name="flexRadioDefault3" id={"flexRadioDefault2" + detail.route_id} onClick={() => {setExportShipRoute(detail)}}   />
                                                        }
                                                    </div>
                                                </td>
                                                <td>
                                                    <button  type="button" style={{backgroundColor:"#6c7ae0"}} className="btn btn-primary" onClick={() => exportActionShipRoute(detail.ship_route_id)}>{props.hubview}</button>
                                                </td>
                                        </tr>
                                    )
                                )
                          
                            }

                        </tbody>
                    </table>
                </div>
                    <div className="route-detail col-md-7">
                        {
                            (props.urlPath.includes("import") || props.urlPath.includes("export"))
                            ?
                            (props.urlPath.includes("import")) ? <ImportShipRouteDetail shipRouteDetail={importShipRoute} /> : <ExportShipRouteDetail shipRouteDetail={exportShipRoute}/>
                            :
                            <ShipRouteDetail shipRouteDetail={shipRoute}  />
                        }
                        
                    </div>
            </div>
            :""}
        </div>
    );
};

const checkShipImported = function(pkgList) {
    let checked = false;
    pkgList.forEach(
        pkg => 
        {if (pkg.current_hub === '-1' && pkg.tracking_status.includes("picked") && !pkg.tracking_status.includes("deliver")) checked = true;}
    );
        return checked;
};

const checkShipExported = function(pkgList) {
    let checked = false;
    pkgList.forEach(
        pkg => {
            if (pkg.current_hub !== "-1" && pkg.current_shipper !== -1 && pkg.next_hub === 'final' && pkg.tracking_status.includes("inhub")) checked = true;
        }
    );
    return checked;
}

export default TodayList