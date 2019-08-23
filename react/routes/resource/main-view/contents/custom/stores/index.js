import React, { createContext, useContext, useMemo } from 'react';
import { DataSet } from 'choerodon-ui/pro';
import { inject } from 'mobx-react';
import { injectIntl } from 'react-intl';
import TableDataSet from './TableDataSet';
import { useResourceStore } from '../../../../stores';

const Store = createContext();

export function useCustomStore() {
  return useContext(Store);
}

export const StoreProvider = injectIntl(inject('AppState')(
  (props) => {
    const { AppState: { currentMenuType: { id } }, children } = props;
    const {
      intlPrefix,
      intl: { formatMessage },
      resourceStore: { getSelectedMenu: { parentId } },
    } = useResourceStore();
    const customDs = useMemo(() => new DataSet(TableDataSet({
      formatMessage,
      intlPrefix,
      projectId: id,
      envId: parentId,
    })), [formatMessage, id, intlPrefix, parentId]);

    const value = {
      ...props,
      customDs,
    };

    return (
      <Store.Provider value={value}>
        {children}
      </Store.Provider>
    );
  }
));