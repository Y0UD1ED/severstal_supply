const Report=({provider})=>{
    return(
        <div>
            <table>
                <caption>
                    {provider.providerName}
                </caption>
                <thead>
                    <tr>
                    <th scope="col">Поставка(ИД)</th>
                    <th scope="col">Дата поставки</th>
                    <th scope="col">Продукты</th>
                    </tr>
                </thead>
                <tbody>
                    {provider["supplies"].map(s=>
                        <tr>
                        <th scope="row">{s.supplyId}</th>
                        <td>{s.supplyDate}</td>
                        <td>
                        <table>
                            <thead>
                                <tr>
                                    <th scope="col">Тип продукта</th>
                                    <th scope="col">Продукт</th>
                                    <th scope="col">Цена (руб/кг)</th>
                                    <th scope="col">Вес (гр)</th>
                                    <th scope="col">Стоимость (руб)</th>
                                </tr>
                            </thead>
                        <tbody>
                        {s["products"].map(p=>
                        
                                <tr>
                                    <td>{p.type}</td>
                                    <td>{p.name}</td>
                                    <td>{p.price}</td>
                                    <td>{p.weight}</td>
                                    <td>{p.cost}</td>
                                </tr>
                        
                        )}
                        </tbody>
                        </table>
                        </td>
                    </tr>   
                    )}
                </tbody>
                <tfoot>
                    <tr>
                    <th scope="row" colspan="2">Суммарная стоимость (руб)</th>
                    <td>{provider.totalCost}</td>
                    </tr>
                    <tr>
                        <th scope="row" colspan="2">Суммарный вес (гр)</th>
                        <td>{provider.totalWeight}</td>
                    </tr>
                </tfoot>
        </table>
        </div>
    )
}
export default Report;